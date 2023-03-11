package app.sodeg.sodeg.feature_requests.data.remote.dto

import app.sodeg.sodeg.feature_requests.domain.model.RequestProperty
import com.google.gson.annotations.SerializedName

data class RequestPropertyDTO(
    val id: Int,
    val name: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    val email: String,
    val subject: String,
    val message: String
) {
    fun toRequestProperty(): RequestProperty {
        return RequestProperty(
            id = id,
            name = name,
            phoneNumber = phoneNumber,
            email = email,
            subject = subject,
            message = message
        )
    }
}
