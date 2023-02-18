package com.example.guryihii.feature_properties.data.remote

import com.example.guryihii.feature_properties.data.remote.dto.PropertiesWrapper
import com.example.guryihii.feature_properties.data.remote.dto.PropertyDTO
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PropertyAPI {

    @GET("properties/all/")
    suspend fun getAllProperties(): PropertiesWrapper

    @GET("properties/details/{slug}")
    suspend fun getPropertyDetail(@Path("slug") slug: String): PropertyDTO


    @POST("properties/create/")
    suspend fun postProperty(
        @Body propertyDTO: PropertyDTO
    ): PropertyDTO
}