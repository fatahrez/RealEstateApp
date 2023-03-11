package app.sodeg.sodeg.feature_properties.presentation.seller_property_detail

import app.sodeg.sodeg.feature_properties.domain.model.Property

data class SellerPropertyDetailState(
    val property: Property? = null,
    val isLoading: Boolean = false
)