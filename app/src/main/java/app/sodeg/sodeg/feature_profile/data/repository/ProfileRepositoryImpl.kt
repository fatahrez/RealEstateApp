package app.sodeg.sodeg.feature_profile.data.repository

import app.sodeg.sodeg.core.util.ResultWrapper
import app.sodeg.sodeg.core.util.safeApiCall
import app.sodeg.sodeg.feature_profile.data.remote.ProfileAPI
import app.sodeg.sodeg.feature_profile.domain.model.Profile
import app.sodeg.sodeg.feature_profile.domain.repository.ProfileRepository
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