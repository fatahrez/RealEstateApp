package app.sodeg.sodeg.feature_auth.data.repository

import app.sodeg.sodeg.core.util.ResultWrapper
import app.sodeg.sodeg.core.util.safeApiCall
import app.sodeg.sodeg.feature_auth.data.remote.AuthAPI
import app.sodeg.sodeg.feature_auth.domain.model.User
import app.sodeg.sodeg.feature_auth.domain.repository.AuthRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl(
    private val apiService: AuthAPI,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): AuthRepository {
    override suspend fun postSignInUser(user: User):
            Flow<ResultWrapper<User>> = safeApiCall(ioDispatcher) {
        apiService.postUserSignIn(user.toUserDTO()).toUser()
    }

    override suspend fun postSignUpUser(user: User): Flow<ResultWrapper<User>>
    = safeApiCall(ioDispatcher) {
        apiService.postUserSignUp(user.toUserDTO()).toUser()
    }
}