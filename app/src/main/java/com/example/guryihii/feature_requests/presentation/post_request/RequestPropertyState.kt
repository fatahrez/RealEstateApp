package com.example.guryihii.feature_requests.presentation.post_request

import com.example.guryihii.feature_requests.domain.model.RequestProperty

data class RequestPropertyState(
    val requestProperty: RequestProperty? = null,
    val isLoading: Boolean = false
)
