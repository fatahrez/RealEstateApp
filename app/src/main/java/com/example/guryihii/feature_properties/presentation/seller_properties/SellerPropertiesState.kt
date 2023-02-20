package com.example.guryihii.feature_properties.presentation.seller_properties

import com.example.guryihii.feature_properties.domain.model.Property

data class SellerPropertiesState(
    val properties: List<Property> = emptyList(),
    val isLoading: Boolean = false
)
