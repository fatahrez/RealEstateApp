package com.example.guryihii.feature_auth.presentation.sign_up

import com.example.guryihii.feature_auth.domain.model.User

data class SignUpState(
    val user: User? = null,
    val isLoading: Boolean = false
)
