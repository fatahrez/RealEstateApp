package app.sodeg.sodeg.feature_properties.data.remote.dto

data class PropertiesWrapper(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<PropertyDTO>
)