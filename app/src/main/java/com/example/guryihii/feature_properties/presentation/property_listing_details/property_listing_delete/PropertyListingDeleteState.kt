package com.example.guryihii.feature_properties.presentation.property_listing_details.property_listing_delete

import com.example.guryihii.feature_properties.domain.model.PropertyListing

data class PropertyListingDeleteState(
    val propertyListing: PropertyListing? = null,
    val isLoading: Boolean = false
)
