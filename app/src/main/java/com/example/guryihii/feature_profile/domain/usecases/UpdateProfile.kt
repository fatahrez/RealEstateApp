package com.example.guryihii.feature_profile.domain.usecases

import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.feature_profile.domain.model.Profile
import com.example.guryihii.feature_profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

class UpdateProfile(
    private val repository: ProfileRepository
) {

    suspend operator fun invoke(
        username: String,
        phoneNumber: MultipartBody.Part,
        aboutMe: MultipartBody.Part,
        license: MultipartBody.Part,
        gender: MultipartBody.Part,
        country: MultipartBody.Part,
        city: MultipartBody.Part,
        profilePhoto: MultipartBody.Part
    ): Flow<ResultWrapper<Profile>> {
        return repository.updateProfile(
            username,
            phoneNumber,
            aboutMe,
            license,
            gender,
            country,
            city,
            profilePhoto
        )
    }

}