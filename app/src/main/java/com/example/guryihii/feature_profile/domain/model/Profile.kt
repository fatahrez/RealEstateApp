package com.example.guryihii.feature_profile.domain.model

import com.example.guryihii.feature_profile.data.remote.dto.ProfileDTO

data class Profile(
    val username: String? = null,
    val firstName: String? = null,
    val email: String? = null,
    val id: Int? = null,
    val phoneNumber: String,
    val profilePhoto: String? = null,
    val aboutMe: String,
    val license: String?,
    val gender: String,
    val country: String,
    val city: String,
    val rating: Any? = null,
    val numReviews: Int? = null
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
