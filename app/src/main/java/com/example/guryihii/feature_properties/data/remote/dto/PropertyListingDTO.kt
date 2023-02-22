package com.example.guryihii.feature_properties.data.remote.dto

import com.example.guryihii.feature_properties.domain.model.PropertyListing

data class PropertyListingDTO(
    val id: Int,
    val property: PropertyDTO,
    val agent: AgentDTO
) {
    fun toPropertyListing(): PropertyListing {
        return PropertyListing(
            id = id,
            property = property.toProperty(),
            agent = agent.toAgent()
        )
    }
}
