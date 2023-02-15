package com.example.guryihii.core.shared.remote

import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import com.example.guryihii.core.util.Constants
import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.feature_refresh_token.data.repository.RefreshTokenRepositoryImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject
import javax.inject.Provider

class TokenAuthenticator @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val repositoryProvider: Provider<RefreshTokenRepositoryImpl>
): Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        return runBlocking {
            Log.i("TAG", "authenticate: here")
            Log.i("TAG", "authenticate: ${response.code}")
            if (response.code == 401) {
                val refreshToken = sharedPreferences.getString(Constants.REFRESH_TOKEN, "")
                val updatedAccessToken = refreshToken?.let {
                    refreshAccessToken(it)
                        .onEach { result ->
                            when (result) {
                                is ResultWrapper.Success -> {
                                    Log.i("TAG", "authenticate: ${result.value}")
                                    result.value
                                }
                                is ResultWrapper.GenericError -> {
                                    Log.i("TAG", "authenticate: ${result.error}")
                                }
                                is ResultWrapper.NetworkError -> {
                                    Log.i("TAG", "Network Error")
                                }
                                is ResultWrapper.Loading -> {
                                    Log.i("TAG", "Loading ... ")
                                }
                            }
                        }
                }
                response.request.newBuilder()
                    .header("Authorization", "Bearer $updatedAccessToken")
                    .build()
            } else {
                null
            }
        }

    }

    private suspend fun refreshAccessToken(refreshToken: String): Flow<ResultWrapper<String>> {
        val repository = repositoryProvider.get()
        return repository.refreshToken(refreshToken)
    }
}