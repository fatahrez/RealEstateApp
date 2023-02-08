package com.example.guryihii.feature_properties.presentation.property_list

import com.example.guryihii.feature_properties.domain.model.Property

data class PropertyListState(
    val properties: List<Property> = emptyList(),
    val isLoading: Boolean = false
)