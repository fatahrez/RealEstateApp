package com.example.guryihii.feature_properties.presentation.agent_listings

import com.example.guryihii.feature_properties.domain.model.PropertyListing

data class MyListingsState(
    val propertyListings: List<PropertyListing> = emptyList(),
    val isLoading: Boolean = false
)
