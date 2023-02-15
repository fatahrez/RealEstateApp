package com.example.guryihii.feature_refresh_token.domain.repository

import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.feature_refresh_token.domain.model.Token
import kotlinx.coroutines.flow.Flow

interface RefreshTokenRepository {

    suspend fun refreshToken(token: String): Token

}