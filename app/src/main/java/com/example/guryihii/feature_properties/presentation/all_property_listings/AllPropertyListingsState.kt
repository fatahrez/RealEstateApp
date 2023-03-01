package com.example.guryihii.feature_properties.presentation.all_property_listings

import com.example.guryihii.feature_properties.domain.model.PropertyListing

data class AllPropertyListingsState(
    val propertyListings: List<PropertyListing> = emptyList(),
    val isLoading: Boolean = false
)
