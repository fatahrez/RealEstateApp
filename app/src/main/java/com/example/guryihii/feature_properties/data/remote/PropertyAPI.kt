package com.example.guryihii.feature_properties.data.remote

import com.example.guryihii.feature_properties.data.remote.dto.PropertiesWrapper
import com.example.guryihii.feature_properties.data.remote.dto.PropertyDTO
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface PropertyAPI {

    @GET("properties/all/")
    suspend fun getAllProperties(): PropertiesWrapper

    @GET("properties/details/{slug}")
    suspend fun getPropertyDetail(@Path("slug") slug: String): PropertyDTO


    @Multipart
    @POST("properties/create/")
    suspend fun postProperty(
        @Part("advert_type") advertType: String,
        @Part("bathrooms") bathrooms: String,
        @Part("bedrooms") bedrooms: Int,
        @Part("city") city: String,
        @Part("country") country: String,
        @Part cover_photo: MultipartBody.Part,
        @Part("description") description: String,
        @Part photo1: MultipartBody.Part,
        @Part photo2: MultipartBody.Part,
        @Part photo3: MultipartBody.Part,
        @Part photo4: MultipartBody.Part,
        @Part("plot_area") plotArea: String,
        @Part("postal_code") postalCode: String,
        @Part("price") price: String,
        @Part("property_number") propertyNumber: String,
        @Part("property_type") propertyType: String,
        @Part("street_address") streetAddress: String,
        @Part("title") title: String,
        @Part("total_floors") totalFloors: Int,
    ): PropertyDTO
}