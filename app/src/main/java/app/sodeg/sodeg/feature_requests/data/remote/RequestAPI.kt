package app.sodeg.sodeg.feature_requests.data.remote

import app.sodeg.sodeg.feature_requests.data.remote.dto.RequestPropertyDTO
import app.sodeg.sodeg.feature_requests.data.remote.dto.RequestPropertyResponseDTO
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RequestAPI {

    @GET("enquiry/all/")
    suspend fun getAllRequests(): List<RequestPropertyDTO>

    @FormUrlEncoded
    @POST("enquiry/create/")
    suspend fun postRequest(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("phone_number") phoneNumber: String,
        @Field("subject") subject: String,
        @Field("message") message: String
    ): RequestPropertyResponseDTO

    @GET("enquiry/details/{id}/")
    suspend fun getRequestDetails(@Path("id") id: Int): RequestPropertyDTO

}