package app.sodeg.sodeg.feature_properties.domain.model

data class Agent(
    val id: Int,
    val email: String,
    val firstName: String,
    val username: String,
    val profilePhoto: String,
    val phoneNumber: String
)
