package com.example.guryihii.feature_refresh_token.data.repository

import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.core.util.safeApiCall
import com.example.guryihii.feature_refresh_token.data.remote.RefreshTokenApi
import com.example.guryihii.feature_refresh_token.domain.repository.RefreshTokenRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RefreshTokenRepositoryImpl @Inject constructor(
    private val refreshTokenApi: RefreshTokenApi,
    private val ioDispatchers: CoroutineDispatcher = Dispatchers.IO
): RefreshTokenRepository {
    override suspend fun refreshToken(token: String): Flow<ResultWrapper<String>>
    = safeApiCall(ioDispatchers) {
        refreshTokenApi.refreshToken(token)
    }
}