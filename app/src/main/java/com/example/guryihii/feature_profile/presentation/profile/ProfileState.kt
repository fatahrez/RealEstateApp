package com.example.guryihii.feature_profile.presentation.profile

import com.example.guryihii.feature_profile.domain.model.Profile

data class ProfileState(
    val profile: Profile? = null,
    val isLoading: Boolean = false
)
