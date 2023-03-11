package app.sodeg.sodeg.feature_agents.data.remote.dto

import app.sodeg.sodeg.feature_agents.domain.model.Agent
import com.google.gson.annotations.SerializedName

data class AgentDTO(
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
    val rating: Double?,
    @SerializedName("num_reviews")
    val numReviews: Int
) {
    fun toAgent(): Agent {
        return Agent(
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
