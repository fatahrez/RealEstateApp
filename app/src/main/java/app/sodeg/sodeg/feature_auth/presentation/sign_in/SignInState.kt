package app.sodeg.sodeg.feature_auth.presentation.sign_in

import app.sodeg.sodeg.feature_auth.domain.model.User

data class SignInState(
    val user: User? = null,
    val isLoading: Boolean = false,
    val errors: String? = ""
)
