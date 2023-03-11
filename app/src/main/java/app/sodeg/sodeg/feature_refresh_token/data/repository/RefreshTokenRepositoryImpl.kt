package app.sodeg.sodeg.feature_refresh_token.data.repository

import app.sodeg.sodeg.feature_refresh_token.data.remote.RefreshTokenApi
import app.sodeg.sodeg.feature_refresh_token.domain.model.Token
import app.sodeg.sodeg.feature_refresh_token.domain.repository.RefreshTokenRepository
import javax.inject.Inject

class RefreshTokenRepositoryImpl @Inject constructor(
    private val refreshTokenApi: RefreshTokenApi
): RefreshTokenRepository {
    override suspend fun refreshToken(token: String): Token {
        return refreshTokenApi.refreshToken(token).toToken()
    }
}