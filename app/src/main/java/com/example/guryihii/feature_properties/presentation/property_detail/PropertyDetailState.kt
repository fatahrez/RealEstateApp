package com.example.guryihii.feature_properties.presentation.property_detail

import com.example.guryihii.feature_properties.domain.model.Property


data class PropertyDetailState(
    val property: Property? = null,
    val isLoading: Boolean = false
)
