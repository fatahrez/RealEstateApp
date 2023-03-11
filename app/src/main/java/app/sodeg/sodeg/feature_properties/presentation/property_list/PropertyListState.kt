package app.sodeg.sodeg.feature_properties.presentation.property_list

import app.sodeg.sodeg.feature_properties.domain.model.Property

data class PropertyListState(
    val properties: List<Property> = emptyList(),
    val isLoading: Boolean = false
)