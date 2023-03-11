package app.sodeg.sodeg.feature_requests.presentation.request_list

import app.sodeg.sodeg.feature_requests.domain.model.RequestProperty

data class PropertyRequestsState(
    val requestProperties: List<RequestProperty> = emptyList(),
    val isLoading: Boolean = false
)
