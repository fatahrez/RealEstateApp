package app.sodeg.sodeg.feature_properties.presentation.property_listing_details

import app.sodeg.sodeg.feature_properties.domain.model.PropertyListing

data class PropertyListingDetailsState(
    val propertyListing: PropertyListing? = null,
    val isLoading: Boolean = false
)
