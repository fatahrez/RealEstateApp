package com.example.guryihii.feature_agents.domain.model

data class Agent(
    val username: String,
    val firstName: String,
    val email: String,
    val id: Int,
    val phoneNumber: String,
    val profilePhoto: String,
    val aboutMe: String,
    val license: String?,
    val gender: String,
    val country: String,
    val city: String,
    val rating: Double?,
    val numReviews: Int
)
