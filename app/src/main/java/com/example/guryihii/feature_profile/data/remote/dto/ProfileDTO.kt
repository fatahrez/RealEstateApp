package com.example.guryihii.feature_profile.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ProfileDTO(
    val username: String,
    @SerializedName("first_name")
    val firstName: String,
    val email: String,
    val id: Int,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("profile_photo")
    val profilePhoto: String,
    @SerializedName("about_me")
    val aboutMe: String,
    val license: String?,
    val gender: String,
    val country: String,
    val city: String,
    val rating: Any?,
    @SerializedName("num_reviews")
    val numReviews: Int
)