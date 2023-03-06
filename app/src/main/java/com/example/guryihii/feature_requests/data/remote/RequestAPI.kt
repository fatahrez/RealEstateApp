package com.example.guryihii.feature_requests.data.remote

import com.example.guryihii.feature_requests.data.remote.dto.RequestPropertyDTO
import com.example.guryihii.feature_requests.data.remote.dto.RequestPropertyResponseDTO
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

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

}