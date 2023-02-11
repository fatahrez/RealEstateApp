package com.example.guryihii.feature_auth.data.repository

import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.core.util.safeApiCall
import com.example.guryihii.feature_auth.data.remote.AuthAPI
import com.example.guryihii.feature_auth.domain.model.User
import com.example.guryihii.feature_auth.domain.repository.AuthRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl(
    private val apiService: AuthAPI,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): AuthRepository {
    override suspend fun postSignInUser(user: User):
            Flow<ResultWrapper<User>> = safeApiCall(ioDispatcher) {
        apiService.postUserSignIn(user).toUser()
    }
}