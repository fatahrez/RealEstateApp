package app.sodeg.sodeg.core.util

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("message")
    @Expose
    val message: String?,
    @SerializedName("errors")
    @Expose
    val errors: Errors<Any>?
)

data class  Errors<T>(
    val t: List<String>
)