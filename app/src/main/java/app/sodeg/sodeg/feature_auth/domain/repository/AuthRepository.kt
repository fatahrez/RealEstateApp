package app.sodeg.sodeg.feature_auth.domain.repository

import app.sodeg.sodeg.core.util.ResultWrapper
import app.sodeg.sodeg.feature_auth.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun postSignInUser(user: User): Flow<ResultWrapper<User>>

    suspend fun postSignUpUser(user: User): Flow<ResultWrapper<User>>

}