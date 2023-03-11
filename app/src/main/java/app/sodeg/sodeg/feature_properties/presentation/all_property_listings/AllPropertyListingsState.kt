package app.sodeg.sodeg.feature_properties.presentation.all_property_listings

import app.sodeg.sodeg.feature_properties.domain.model.PropertyListing

data class AllPropertyListingsState(
    val propertyListings: List<PropertyListing> = emptyList(),
    val isLoading: Boolean = false
)
