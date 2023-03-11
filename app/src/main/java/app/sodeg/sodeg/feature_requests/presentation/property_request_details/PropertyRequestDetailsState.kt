package app.sodeg.sodeg.feature_requests.presentation.property_request_details

import app.sodeg.sodeg.feature_requests.domain.model.RequestProperty

data class PropertyRequestDetailsState(
    val requestProperty: RequestProperty? = null,
    val isLoading: Boolean = false
)
