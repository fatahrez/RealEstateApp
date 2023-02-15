package com.example.guryihii.feature_refresh_token.data.remote

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RefreshTokenApi {

    @FormUrlEncoded
    @POST("users/refresh/")
    suspend fun refreshToken(@Field("refresh") token: String): String

}