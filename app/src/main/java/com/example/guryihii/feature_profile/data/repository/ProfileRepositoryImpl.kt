package com.example.guryihii.feature_profile.data.repository

import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.core.util.safeApiCall
import com.example.guryihii.feature_profile.data.remote.ProfileAPI
import com.example.guryihii.feature_profile.domain.model.Profile
import com.example.guryihii.feature_profile.domain.repository.ProfileRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class ProfileRepositoryImpl(
    private val profileAPI: ProfileAPI,
    private val ioDispatchers: CoroutineDispatcher = Dispatchers.IO
): ProfileRepository {
    override suspend fun getProfile(): Flow<ResultWrapper<Profile>>
    = safeApiCall(ioDispatchers) {
        profileAPI.getProfile().profile.toProfile()
    }
}