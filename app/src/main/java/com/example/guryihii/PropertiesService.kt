package com.example.guryihii

import retrofit2.Response
import retrofit2.http.GET

interface PropertiesService {
    @GET("http://24.199.124.221/api/properties/all/")
    suspend fun getAllProperties(): Response<NetworkProperties>
}
