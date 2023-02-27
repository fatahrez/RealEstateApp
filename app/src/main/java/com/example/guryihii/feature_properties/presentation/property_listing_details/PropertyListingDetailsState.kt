package com.example.guryihii.feature_properties.presentation.property_listing_details

import com.example.guryihii.feature_properties.domain.model.PropertyListing

data class PropertyListingDetailsState(
    val propertyListing: PropertyListing? = null,
    val isLoading: Boolean = false
)
