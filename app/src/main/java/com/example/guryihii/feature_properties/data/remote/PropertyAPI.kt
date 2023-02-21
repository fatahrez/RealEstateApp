package com.example.guryihii.feature_properties.data.remote

import com.example.guryihii.feature_properties.data.remote.dto.PropertiesWrapper
import com.example.guryihii.feature_properties.data.remote.dto.PropertyDTO
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface PropertyAPI {

    @GET("properties/all/")
    suspend fun getAllProperties(): PropertiesWrapper

    @GET("properties/details/{slug}")
    suspend fun getPropertyDetail(@Path("slug") slug: String): PropertyDTO


    @POST("properties/create/")
    @Multipart
    suspend fun postProperty(
        @Part advertType: MultipartBody.Part,
        @Part("bathrooms") bathrooms: Int,
        @Part("bedrooms") bedrooms: Int,
        @Part("city") city: String,
        @Part country: MultipartBody.Part,
        @Part cover_photo: MultipartBody.Part,
        @Part("description") description: String,
        @Part photo1: MultipartBody.Part,
        @Part photo2: MultipartBody.Part,
        @Part photo3: MultipartBody.Part,
        @Part photo4: MultipartBody.Part,
        @Part("plot_area") plotArea: Int,
        @Part("postal_code") postalCode: String,
        @Part("price") price: Int,
        @Part("property_number") propertyNumber: Int,
        @Part propertyType: MultipartBody.Part,
        @Part("street_address") streetAddress: String,
        @Part("title") title: String,
        @Part("total_floors") totalFloors: Int
    ): PropertyDTO

    @GET("properties/agents/")
    suspend fun getSellerProperties(): PropertiesWrapper

    @PUT("properties/update/{slug}/")
    @Multipart
    suspend fun updateProperty(
        @Path("slug") slug: String,
        @Part advertType: MultipartBody.Part? = null,
        @Part("bathrooms") bathrooms: Int? = null,
        @Part("bedrooms") bedrooms: Int? = null,
        @Part("city") city: String? = null,
        @Part country: MultipartBody.Part? = null,
        @Part cover_photo: MultipartBody.Part? = null,
        @Part("description") description: String? = null,
        @Part photo1: MultipartBody.Part? = null,
        @Part photo2: MultipartBody.Part? = null,
        @Part photo3: MultipartBody.Part? = null,
        @Part photo4: MultipartBody.Part? = null,
        @Part("plot_area") plotArea: Int? = null,
        @Part("postal_code") postalCode: String? = null,
        @Part("price") price: Int? = null,
        @Part("property_number") propertyNumber: Int? = null,
        @Part propertyType: MultipartBody.Part? = null,
        @Part("street_address") streetAddress: String? = null,
        @Part("title") title: String? = null,
        @Part("total_floors") totalFloors: Int? = null,
        @Part("user") user: Int
    ): PropertyDTO
}