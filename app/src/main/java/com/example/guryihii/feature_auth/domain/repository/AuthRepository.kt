package com.example.guryihii.feature_auth.domain.repository

import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.feature_auth.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun postSignInUser(user: User): Flow<ResultWrapper<User>>

}