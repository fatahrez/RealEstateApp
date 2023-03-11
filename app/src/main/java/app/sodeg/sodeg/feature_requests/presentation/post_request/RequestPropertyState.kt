package app.sodeg.sodeg.feature_requests.presentation.post_request

import app.sodeg.sodeg.feature_requests.domain.model.RequestPropertyResponse

data class RequestPropertyState(
    val requestPropertyResponse: RequestPropertyResponse? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
