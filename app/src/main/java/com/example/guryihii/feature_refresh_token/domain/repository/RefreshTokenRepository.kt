package com.example.guryihii.feature_refresh_token.domain.repository

import com.example.guryihii.core.util.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface RefreshTokenRepository {

    suspend fun refreshToken(token: String): Flow<ResultWrapper<String>>

}