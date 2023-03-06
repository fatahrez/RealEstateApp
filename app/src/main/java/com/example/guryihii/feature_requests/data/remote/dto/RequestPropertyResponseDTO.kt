package com.example.guryihii.feature_requests.data.remote.dto

import com.example.guryihii.feature_requests.domain.model.RequestProperty
import com.example.guryihii.feature_requests.domain.model.RequestPropertyResponse

data class RequestPropertyResponseDTO(
    val data: RequestPropertyDTO,
    val success: String
) {
    fun toRequestPropertyResponse(): RequestPropertyResponse {
        return RequestPropertyResponse(
            data = data.toRequestProperty(),
            success = success
        )
    }
}
