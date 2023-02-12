package com.example.guryihii.feature_auth.data.remote

import android.content.SharedPreferences
import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.core.util.safeApiCall
import com.example.guryihii.feature_auth.domain.model.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class TokenAuthenticator @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val authAPI: AuthAPI,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        return runBlocking {
            if (response.code == 401) {
                getUpdatedToken().onEach {result ->
                    when(result) {
                        is ResultWrapper.Success -> {
                            sharedPreferences
                                .edit()
                                .putString("access_token", result.value)
                                .apply()
                            response.request.newBuilder()
                                .header("Authorization", "Bearer ${result.value}")
                                .build()
                        }
                        else -> null
                    }
                }
            }
            null
        }

    }

    private suspend fun getUpdatedToken(): Flow<ResultWrapper<String>> = safeApiCall(ioDispatcher) {
        val refreshToken = sharedPreferences.getString("refresh_token", null)
        if (refreshToken != null) {
            authAPI.postRefreshAccessToken(refreshToken)
        } else {
            ""
        }
    }
}