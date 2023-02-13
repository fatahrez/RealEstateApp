package com.example.guryihii.feature_auth.data.remote.dto

import com.example.guryihii.feature_auth.domain.model.User
import com.google.gson.annotations.SerializedName

data class UserDTO(
    val email: String? = null,
    val password: String? = null,
    val username: String? = null,
    @SerializedName("access")
    val accessToken: String? = null,
    @SerializedName("refresh")
    val refreshToken: String? = null,
    @SerializedName("first_name")
    val firstName: String? = null,
    val type: String? = null
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
