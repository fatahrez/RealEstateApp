package com.example.guryihii.feature_auth.domain.usecases

import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.feature_auth.domain.model.User
import com.example.guryihii.feature_auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class PostSignInUser(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(user: User): Flow<ResultWrapper<User>> {
        return repository.postSignInUser(user)
    }

}