package com.example.guryihii.feature_properties.presentation.post_property

import com.example.guryihii.feature_properties.domain.model.Property
import com.example.guryihii.feature_properties.domain.model.response.PropertyResponse

data class PostPropertyState(
    val property: PropertyResponse? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
