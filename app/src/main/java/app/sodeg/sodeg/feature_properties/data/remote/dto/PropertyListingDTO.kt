package app.sodeg.sodeg.feature_properties.data.remote.dto

import app.sodeg.sodeg.feature_properties.domain.model.PropertyListing

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
