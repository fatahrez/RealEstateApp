package com.example.guryihii.feature_auth.domain.model

data class User(
    val email: String,
    val password: String,
    val username: String?,
    val accessToken: String?,
    val refreshToken: String?,
    val firstName: String?,
    val type: String?
)
