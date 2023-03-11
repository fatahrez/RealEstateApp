package app.sodeg.sodeg.feature_properties.presentation.property_detail

import app.sodeg.sodeg.feature_properties.domain.model.Property


data class PropertyDetailState(
    val property: Property? = null,
    val isLoading: Boolean = false
)
