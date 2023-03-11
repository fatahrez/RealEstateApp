package app.sodeg.sodeg.core.util.jwt

import com.google.gson.annotations.SerializedName

data class JwtPayload(
    @SerializedName("token_type")
    val tokenType: String,
    val exp: Long,
    val iat: Long,
    val jti: String,
    @SerializedName("user_id")
    val userId: Int,
    val role: String,
    val username: String
)
