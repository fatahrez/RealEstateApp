package com.example.guryihii.feature_auth.data.remote.dto

import com.example.guryihii.feature_auth.domain.model.User
import com.google.gson.annotations.SerializedName

data class UserDTO(
    val email: String,
    val password: String,
    val username: String?,
    @SerializedName("access")
    val accessToken: String?,
    @SerializedName("refresh")
    val refreshToken: String?,
    @SerializedName("first_name")
    val firstName: String?,
    val type: String?
) {
    fun toUser(): User {
        return User(
            email = email,
            password = password,
            username = username,
            accessToken = accessToken,
            refreshToken = refreshToken,
            firstName = firstName,
            type = type
        )
    }
}
