package app.sodeg.sodeg.feature_refresh_token.data.remote

import app.sodeg.sodeg.feature_refresh_token.data.remote.dto.TokenDTO
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RefreshTokenApi {

    @FormUrlEncoded
    @POST("users/refresh/")
    suspend fun refreshToken(@Field("refresh") token: String): TokenDTO

}