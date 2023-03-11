package app.sodeg.sodeg.feature_requests.data.remote.dto

import app.sodeg.sodeg.feature_requests.domain.model.RequestPropertyResponse

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
