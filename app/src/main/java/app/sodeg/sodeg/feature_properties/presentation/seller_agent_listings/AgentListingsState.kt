package app.sodeg.sodeg.feature_properties.presentation.seller_agent_listings

import app.sodeg.sodeg.feature_properties.domain.model.PropertyListing

data class AgentListingsState(
    val propertyListings: List<PropertyListing> = emptyList(),
    val isLoading: Boolean = false
)
