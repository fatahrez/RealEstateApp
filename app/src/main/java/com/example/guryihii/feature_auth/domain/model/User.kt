package com.example.guryihii.feature_auth.domain.model

data class User(
    val email: String,
    val password: String,
    val username: String? = null,
    val accessToken: String? = null,
    val refreshToken: String? = null,
    val firstName: String? = null,
    val type: String? = null
)
