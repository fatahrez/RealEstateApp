package app.sodeg.sodeg.feature_refresh_token.domain.repository

import app.sodeg.sodeg.feature_refresh_token.domain.model.Token

interface RefreshTokenRepository {

    suspend fun refreshToken(token: String): Token

}