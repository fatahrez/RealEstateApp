package com.example.guryihii.core.shared.remote

import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import com.example.guryihii.core.util.Constants
import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.feature_refresh_token.data.repository.RefreshTokenRepositoryImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
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
    private val repositoryProvider: Provider<RefreshTokenRepositoryImpl>,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        return runBlocking {
            Log.i("TAG", "authenticate: here")
            Log.i("TAG", "authenticate: ${response.code}")
            if (response.code == 401) {
                Log.i("TAG", "authenticate: hereeeeee")
                val refreshToken = sharedPreferences.getString(Constants.REFRESH_TOKEN, "")
                val updatedAccessToken = refreshToken?.let { refreshAccessToken(it) }
                response.request.newBuilder()
                    .header("Authorization", "Bearer $updatedAccessToken")
                    .build()
            } else {
                null
            }
        }

    }

    private suspend fun refreshAccessToken(refreshToken: String): String
    = withContext(ioDispatcher){
        val repository = repositoryProvider.get()
        repository.refreshToken(refreshToken)
    }
}