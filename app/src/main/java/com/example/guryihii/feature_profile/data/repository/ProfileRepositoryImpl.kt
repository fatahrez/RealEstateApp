package com.example.guryihii.feature_profile.data.repository

import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.core.util.safeApiCall
import com.example.guryihii.feature_profile.data.remote.ProfileAPI
import com.example.guryihii.feature_profile.domain.model.Profile
import com.example.guryihii.feature_profile.domain.repository.ProfileRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

class ProfileRepositoryImpl(
    private val profileAPI: ProfileAPI,
    private val ioDispatchers: CoroutineDispatcher = Dispatchers.IO
): ProfileRepository {
    override suspend fun getProfile(): Flow<ResultWrapper<Profile>>
    = safeApiCall(ioDispatchers) {
        profileAPI.getProfile().profile.toProfile()
    }

    override suspend fun updateProfile(
        username: String,
        phoneNumber: MultipartBody.Part,
        aboutMe: MultipartBody.Part,
        license: MultipartBody.Part,
        gender: MultipartBody.Part,
        country: MultipartBody.Part,
        city: MultipartBody.Part,
        profilePhoto: MultipartBody.Part
    ): Flow<ResultWrapper<Profile>> = safeApiCall(ioDispatchers) {
        profileAPI.updateProfile(
            username,
            phoneNumber,
            aboutMe,
            license,
            gender,
            country,
            city,
            profilePhoto
        ).profile.toProfile()
    }

}