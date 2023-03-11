package app.sodeg.sodeg.feature_profile.presentation.profile

import app.sodeg.sodeg.feature_profile.domain.model.Profile

data class ProfileState(
    val profile: Profile? = null,
    val isLoading: Boolean = false
)
