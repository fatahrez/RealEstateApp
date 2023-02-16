package com.example.guryihii.feature_profile.data.remote.dto

import com.example.guryihii.feature_profile.domain.model.Profile
import com.google.gson.annotations.SerializedName

data class ProfileDTO(
    val username: String? = null,
    @SerializedName("first_name")
    val firstName: String? = null,
    val email: String? = null,
    val id: Int? = null,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("profile_photo")
    val profilePhoto: String? = null,
    @SerializedName("about_me")
    val aboutMe: String,
    val license: String?,
    val gender: String,
    val country: String,
    val city: String,
    val rating: Any? = null,
    @SerializedName("num_reviews")
    val numReviews: Int? = null
) {
    fun toProfile(): Profile {
        return Profile(
            username = username,
            firstName = firstName,
            email = email,
            id = id,
            phoneNumber = phoneNumber,
            profilePhoto = profilePhoto,
            aboutMe = aboutMe,
            license = license,
            gender = gender,
            country = country,
            city = city,
            rating = rating,
            numReviews = numReviews
        )
    }
}