package com.example.guryihii.feature_profile.domain.usecases

import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.feature_profile.domain.model.Profile
import com.example.guryihii.feature_profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow

class UpdateProfile(
    private val repository: ProfileRepository
) {

    suspend operator fun invoke(username: String, profile: Profile): Flow<ResultWrapper<String>> {
        return repository.updateProfile(username, profile)
    }

}