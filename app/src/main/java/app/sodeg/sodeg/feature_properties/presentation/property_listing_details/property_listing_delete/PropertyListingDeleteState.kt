package app.sodeg.sodeg.feature_properties.presentation.property_listing_details.property_listing_delete

import app.sodeg.sodeg.feature_properties.domain.model.PropertyListing

data class PropertyListingDeleteState(
    val propertyListing: PropertyListing? = null,
    val isLoading: Boolean = false
)
