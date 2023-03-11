package app.sodeg.sodeg.feature_auth.domain.usecases

import app.sodeg.sodeg.core.util.ResultWrapper
import app.sodeg.sodeg.feature_auth.domain.model.User
import app.sodeg.sodeg.feature_auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class PostSignInUser(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(user: User): Flow<ResultWrapper<User>> {
        return repository.postSignInUser(user)
    }

}