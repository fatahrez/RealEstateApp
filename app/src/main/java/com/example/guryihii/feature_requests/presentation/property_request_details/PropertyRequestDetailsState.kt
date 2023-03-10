package com.example.guryihii.feature_requests.presentation.property_request_details

import com.example.guryihii.feature_requests.domain.model.RequestProperty

data class PropertyRequestDetailsState(
    val requestProperty: RequestProperty? = null,
    val isLoading: Boolean = false
)
