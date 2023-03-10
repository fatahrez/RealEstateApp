package com.example.guryihii.feature_properties.presentation.post_property

import com.example.guryihii.feature_properties.domain.model.Property

data class PostPropertyState(
    val property: Property? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
