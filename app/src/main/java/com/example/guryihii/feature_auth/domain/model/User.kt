package com.example.guryihii.feature_auth.domain.model

data class User(
    val email: String,
    val password: String,
    val username: String?,
    val access_token: String?,
    val refresh_token: String?,
    val firstName: String?,
    val type: String?
)
