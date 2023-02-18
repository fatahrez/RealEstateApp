package com.example.guryihii.feature_properties.data.remote

import com.example.guryihii.feature_properties.data.remote.dto.PropertiesWrapper
import com.example.guryihii.feature_properties.data.remote.dto.PropertyDTO
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
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
    @FormUrlEncoded
    @POST("properties/create/")
    suspend fun postProperty(
        @Field("advert_type") advertType: String,
        @Field("bathrooms") bathrooms: Int,
        @Field("city") city: String,
        @Field("country") country: String,
        @Part("cover_photo") coverPhoto: MultipartBody.Part,
        @Field("description") description: String,
        @Part("photo1") photo1: MultipartBody.Part,
        @Part("photo2") photo2: MultipartBody.Part,
        @Part("photo3") photo3: MultipartBody.Part,
        @Part("photo4") photo4: MultipartBody.Part,
        @Field("plot_area") plotArea: String,
        @Field("postal_code") postalCode: String,
        @Field("price") price: String,
        @Field("property_number") propertyNumber: String,
        @Field("property_type") propertyType: String,
        @Field("street_address") streetAddress: String,
        @Field("title") title: String,
        @Field("total_floors") totalFloors: Int,
    ): PropertyDTO
}