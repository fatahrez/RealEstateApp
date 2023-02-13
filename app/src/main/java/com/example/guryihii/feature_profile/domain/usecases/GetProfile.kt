package com.example.guryihii.feature_profile.domain.usecases

import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.feature_profile.domain.model.Profile
import com.example.guryihii.feature_profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow

class GetProfile(
    private val profileRepository: ProfileRepository
) {
    suspend operator fun invoke(): Flow<ResultWrapper<Profile>> {
        return profileRepository.getProfile()
    }
}