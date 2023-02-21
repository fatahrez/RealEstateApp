package com.example.guryihii.feature_properties.presentation.seller_property_detail

import com.example.guryihii.feature_properties.domain.model.Property

data class SellerPropertyDetailState(
    val property: Property? = null,
    val isLoading: Boolean = false
)