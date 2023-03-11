package app.sodeg.sodeg.feature_profile.presentation.update_profile

import app.sodeg.sodeg.feature_profile.domain.model.Profile

data class UpdateProfileState(
    val profileResponse: Profile? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
