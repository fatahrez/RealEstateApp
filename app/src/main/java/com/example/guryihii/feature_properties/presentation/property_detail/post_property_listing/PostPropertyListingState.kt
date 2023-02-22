package com.example.guryihii.feature_properties.presentation.property_detail.post_property_listing

import com.example.guryihii.feature_properties.domain.model.PropertyListing

data class PostPropertyListingState(
    val propertyListing: PropertyListing? = null,
    val isLoading: Boolean = false
)
