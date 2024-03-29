package app.sodeg.sodeg.feature_auth.domain.model

import app.sodeg.sodeg.feature_auth.data.remote.dto.UserDTO


data class User(
    val email: String? = null,
    val password: String? = null,
    val username: String? = null,
    val accessToken: String? = null,
    val refreshToken: String? = null,
    val firstName: String? = null,
    val type: String? = null,
    val detail: String? = null
) {
    fun toUserDTO(): UserDTO {
        return UserDTO(
            email = email,
            password = password,
            username = username,
            accessToken = accessToken,
            refreshToken = refreshToken,
            firstName = firstName,
            type = type,
            detail = detail
        )
    }
}
