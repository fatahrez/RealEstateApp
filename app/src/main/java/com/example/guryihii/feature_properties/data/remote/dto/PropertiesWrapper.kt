package com.example.guryihii.feature_properties.data.remote.dto

import com.example.guryihii.feature_properties.domain.model.Property

data class PropertiesWrapper(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<PropertyDTO>
)