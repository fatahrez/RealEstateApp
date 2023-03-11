package app.sodeg.sodeg.feature_auth.presentation.sign_up

import app.sodeg.sodeg.feature_auth.domain.model.User

data class SignUpState(
    val user: User? = null,
    val isLoading: Boolean = false,
    val error: String? = ""
)
