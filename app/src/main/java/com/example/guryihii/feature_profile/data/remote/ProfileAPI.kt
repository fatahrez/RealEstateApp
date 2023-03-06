package com.example.guryihii.feature_profile.data.remote

import com.example.guryihii.feature_profile.data.remote.dto.ProfileDTO
import com.example.guryihii.feature_profile.data.remote.dto.ProfileResponseWrapper
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface ProfileAPI {

    @GET("profile/me/")
    suspend fun getProfile(): ProfileResponseWrapper

    @PATCH("profile/update/{username}/")
    suspend fun updateProfile(
        @Path("username") username: String,
        @Body profile: ProfileDTO
    ): ProfileResponseWrapper
}