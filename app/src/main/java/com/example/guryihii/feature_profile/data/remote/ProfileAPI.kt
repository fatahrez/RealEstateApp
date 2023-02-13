package com.example.guryihii.feature_profile.data.remote

import com.example.guryihii.feature_profile.data.remote.dto.ProfileDTO
import retrofit2.http.GET

interface ProfileAPI {

    @GET("profile/me/")
    suspend fun getProfile(): ProfileDTO

}