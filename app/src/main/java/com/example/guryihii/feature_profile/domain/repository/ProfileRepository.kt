package com.example.guryihii.feature_profile.domain.repository

import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.feature_profile.domain.model.Profile
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import retrofit2.http.Part

interface ProfileRepository {

    suspend fun getProfile(): Flow<ResultWrapper<Profile>>

    suspend fun updateProfile(
        username: String,
        phoneNumber: MultipartBody.Part,
        aboutMe: MultipartBody.Part,
        license: MultipartBody.Part,
        gender: MultipartBody.Part,
        country: MultipartBody.Part,
        city: MultipartBody.Part,
        profilePhoto: MultipartBody.Part
    ): Flow<ResultWrapper<Profile>>

}