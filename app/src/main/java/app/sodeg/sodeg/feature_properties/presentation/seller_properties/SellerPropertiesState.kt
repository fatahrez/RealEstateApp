package app.sodeg.sodeg.feature_properties.presentation.seller_properties

import app.sodeg.sodeg.feature_properties.domain.model.Property

data class SellerPropertiesState(
    val properties: List<Property> = emptyList(),
    val isLoading: Boolean = false
)
