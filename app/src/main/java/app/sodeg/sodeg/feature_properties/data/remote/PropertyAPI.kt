package app.sodeg.sodeg.feature_properties.data.remote

import app.sodeg.sodeg.feature_properties.data.remote.dto.PropertyDTO
import app.sodeg.sodeg.feature_properties.data.remote.dto.PropertyListingDTO
import app.sodeg.sodeg.feature_properties.data.remote.dto.PropertyResponseDTO
import okhttp3.MultipartBody
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface PropertyAPI {

    @GET("properties/all/")
    suspend fun getAllProperties(): List<PropertyDTO>

    @GET("properties/details/{slug}")
    suspend fun getPropertyDetail(@Path("slug") slug: String): PropertyDTO


    @POST("properties/create/")
    @Multipart
    suspend fun postProperty(
        @Part advertType: MultipartBody.Part,
        @Part("bathrooms") bathrooms: Int,
        @Part("bedrooms") bedrooms: Int,
        @Part city: MultipartBody.Part,
        @Part country: MultipartBody.Part,
        @Part cover_photo: MultipartBody.Part,
        @Part description: MultipartBody.Part,
        @Part photo1: MultipartBody.Part,
        @Part photo2: MultipartBody.Part,
        @Part photo3: MultipartBody.Part,
        @Part photo4: MultipartBody.Part,
        @Part("plot_area") plotArea: Int,
        @Part postalCode: MultipartBody.Part,
        @Part("price") price: Int,
        @Part("property_number") propertyNumber: Int,
        @Part propertyType: MultipartBody.Part,
        @Part streetAddress: MultipartBody.Part,
        @Part title: MultipartBody.Part,
        @Part("total_floors") totalFloors: Int
    ): PropertyResponseDTO

    @GET("properties/agents/")
    suspend fun getSellerProperties(): List<PropertyDTO>

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

    @DELETE("properties/delete/{slug}/")
    suspend fun deleteProperty(
        @Path("slug") slug: String
    ): PropertyDTO

    @FormUrlEncoded
    @POST("propertylisting/create/")
    suspend fun postPropertyListing(
        @Field("property") property: String
    ): PropertyListingDTO

    @GET("propertylisting/agents/all/")
    suspend fun getAgentPropertyListing(): List<PropertyListingDTO>

    @GET("propertylisting/seller/all/")
    suspend fun getSellerPropertyListing(): List<PropertyListingDTO>

    @GET("propertylisting/all/")
    suspend fun getAllPropertyListing(): List<PropertyListingDTO>

    @GET("propertylisting/details/{id}/")
    suspend fun getPropertyListingDetails(@Path("id") id: Int): PropertyListingDTO

    @DELETE("propertylisting/delete/{id}/")
    suspend fun deletePropertyListing(@Path("id") id: Int): PropertyListingDTO
}