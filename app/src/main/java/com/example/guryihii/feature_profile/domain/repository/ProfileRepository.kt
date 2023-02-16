package com.example.guryihii.feature_profile.domain.repository

import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.feature_profile.domain.model.Profile
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    suspend fun getProfile(): Flow<ResultWrapper<Profile>>

    suspend fun updateProfile(username: String, profile: Profile): Flow<ResultWrapper<String>>

}