package com.example.guryihii.feature_auth.presentation.sign_in

import com.example.guryihii.feature_auth.domain.model.User

data class SignInState(
    val user: User? = null,
    val isLoading: Boolean = false,
    val errors: String? = ""
)
