package app.sodeg.sodeg.feature_profile.domain.repository

import app.sodeg.sodeg.core.util.ResultWrapper
import app.sodeg.sodeg.feature_profile.domain.model.Profile
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

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