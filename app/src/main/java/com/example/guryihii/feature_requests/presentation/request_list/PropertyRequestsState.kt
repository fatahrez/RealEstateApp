package com.example.guryihii.feature_requests.presentation.request_list

import com.example.guryihii.feature_requests.domain.model.RequestProperty

data class PropertyRequestsState(
    val requestProperties: List<RequestProperty> = emptyList(),
    val isLoading: Boolean = false
)
