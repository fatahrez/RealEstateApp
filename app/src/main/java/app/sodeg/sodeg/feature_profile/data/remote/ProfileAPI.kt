package app.sodeg.sodeg.feature_profile.data.remote

import app.sodeg.sodeg.feature_profile.data.remote.dto.ProfileResponseWrapper
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.Part
import retrofit2.http.Path

interface ProfileAPI {

    @GET("profile/me/")
    suspend fun getProfile(): ProfileResponseWrapper

    @Multipart
    @PATCH("profile/update/{username}/")
    suspend fun updateProfile(
        @Path("username") username: String,
        @Part phoneNumber: MultipartBody.Part,
        @Part aboutMe: MultipartBody.Part,
        @Part license: MultipartBody.Part,
        @Part gender: MultipartBody.Part,
        @Part country: MultipartBody.Part,
        @Part city: MultipartBody.Part,
        @Part profilePhoto: MultipartBody.Part
    ): ProfileResponseWrapper
}