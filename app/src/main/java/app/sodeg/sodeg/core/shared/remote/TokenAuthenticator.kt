package app.sodeg.sodeg.core.shared.remote

import android.content.SharedPreferences
import app.sodeg.sodeg.core.util.Constants
import app.sodeg.sodeg.feature_refresh_token.data.repository.RefreshTokenRepositoryImpl
import app.sodeg.sodeg.feature_refresh_token.domain.model.Token
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
    private val repositoryProvider: Provider<RefreshTokenRepositoryImpl>,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        if (response.request.url.toString() == Constants.LOGIN_URL) {
            return null
        }
        return runBlocking {
            if (response.code == 401) {
                val refreshToken = sharedPreferences.getString(Constants.REFRESH_TOKEN, "")
                val updatedAccessToken = refreshToken?.let { refreshAccessToken(it).access }
                response.request.newBuilder()
                    .header("Authorization", "Bearer $updatedAccessToken")
                    .build()
            } else {
                null
            }
        }

    }
    private suspend fun refreshAccessToken(refreshToken: String): Token
    = withContext(ioDispatcher){
        val repository = repositoryProvider.get()
        repository.refreshToken(refreshToken)
    }
}