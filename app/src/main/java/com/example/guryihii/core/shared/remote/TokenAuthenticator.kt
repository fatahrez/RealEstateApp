package com.example.guryihii.core.shared.remote

import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import com.example.guryihii.core.util.Constants
import com.example.guryihii.feature_auth.data.remote.AuthApiHolder
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject
import javax.inject.Provider

class TokenAuthenticator @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val authApiHolder: Provider<AuthApiHolder>,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        return runBlocking {
            Log.i("TAG", "authenticate: here")
            Log.i("TAG", "authenticate: ${response.code}")
            if (response.code == 401) {
                try {
                    Log.i("TAG", "authenticate: here}")
                    val token = getUpdatedToken()
                    Log.i("TAG", "authenticate: token $token")
                    sharedPreferences.edit {
                        putString(Constants.ACCESS_TOKEN, token)
                    }
                    response.request.newBuilder().header("Authorization", "Bearer $token")
                } catch (e: Exception) {
                    Log.e("TAG", "authenticate: ", e)
                }
            } else {
                null
            }
            null
        }

    }

    private suspend fun getUpdatedToken(): String = withContext(ioDispatcher) {
        Log.i("TAG", "getUpdatedToken: here again again")
        val refreshToken = sharedPreferences.getString(Constants.REFRESH_TOKEN, null)
        Log.i("TAG", "getUpdatedToken: $refreshToken")
        if (refreshToken != null) {
            authApiHolder.get().apiService().postRefreshAccessToken(refreshToken)
        } else {
            ""
        }
    }
}