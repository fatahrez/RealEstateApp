package com.example.guryihii.feature_requests.domain.model

data class RequestProperty(
    val id: Int,
    val name: String,
    val phoneNumber: String,
    val email: String,
    val subject: String,
    val message: String
)
