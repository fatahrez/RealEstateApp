package com.example.guryihii.feature_requests.presentation.post_request

import com.example.guryihii.feature_requests.domain.model.RequestProperty
import com.example.guryihii.feature_requests.domain.model.RequestPropertyResponse

data class RequestPropertyState(
    val requestPropertyResponse: RequestPropertyResponse? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
