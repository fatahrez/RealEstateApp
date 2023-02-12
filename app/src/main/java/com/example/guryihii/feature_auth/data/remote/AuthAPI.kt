package com.example.guryihii.feature_auth.data.remote

import com.example.guryihii.feature_auth.data.remote.dto.UserDTO
import com.example.guryihii.feature_auth.domain.model.User
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthAPI {

    @POST("users/login/")
    suspend fun postUserSignIn(@Body user: User): UserDTO

    @POST("users/refresh")
    suspend fun postRefreshAccessToken(@Body refreshToken: String): String

}