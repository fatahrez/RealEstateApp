package app.sodeg.sodeg.feature_properties.presentation.agent_listings

import app.sodeg.sodeg.feature_properties.domain.model.PropertyListing

data class MyListingsState(
    val propertyListings: List<PropertyListing> = emptyList(),
    val isLoading: Boolean = false
)
