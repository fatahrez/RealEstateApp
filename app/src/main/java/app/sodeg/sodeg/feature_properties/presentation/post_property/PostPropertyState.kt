package app.sodeg.sodeg.feature_properties.presentation.post_property

import app.sodeg.sodeg.feature_properties.domain.model.response.PropertyResponse

data class PostPropertyState(
    val property: PropertyResponse? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
