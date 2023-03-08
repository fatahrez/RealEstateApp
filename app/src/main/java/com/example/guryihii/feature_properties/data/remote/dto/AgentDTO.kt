package com.example.guryihii.feature_properties.data.remote.dto

import com.example.guryihii.feature_properties.domain.model.Agent
import com.google.gson.annotations.SerializedName

data class AgentDTO(
    val id: Int,
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    val username: String,
    @SerializedName("profile_photo")
    val profilePhoto: String,
    @SerializedName("phone_number")
    val phoneNumber: String
) {
    fun toAgent(): Agent {
        return Agent(
            id = id,
            email = email,
            firstName = firstName,
            username = firstName,
            profilePhoto = profilePhoto,
            phoneNumber = phoneNumber
        )
    }
}
