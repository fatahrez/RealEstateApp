package com.example.guryihii.feature_profile.domain.model

import com.example.guryihii.feature_profile.data.remote.dto.ProfileDTO

data class Profile(
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
    val rating: Any?,
    val numReviews: Int
) {
    fun toProfileDTO(): ProfileDTO {
        return ProfileDTO(
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
