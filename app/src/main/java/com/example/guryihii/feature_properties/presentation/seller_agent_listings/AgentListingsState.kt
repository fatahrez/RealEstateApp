package com.example.guryihii.feature_properties.presentation.seller_agent_listings

import com.example.guryihii.feature_properties.domain.model.PropertyListing

data class AgentListingsState(
    val propertyListings: List<PropertyListing> = emptyList(),
    val isLoading: Boolean = false
)
