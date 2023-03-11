package app.sodeg.sodeg.feature_properties.domain.repository

import app.sodeg.sodeg.core.util.ResultWrapper
import app.sodeg.sodeg.feature_properties.domain.model.Property
import app.sodeg.sodeg.feature_properties.domain.model.PropertyListing
import app.sodeg.sodeg.feature_properties.domain.model.response.PropertyResponse
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

interface PropertyRepository {
    suspend fun getAllProperties(): Flow<ResultWrapper<List<Property>>>

    suspend fun getPropertyDetails(slug: String): Flow<ResultWrapper<Property>>

    suspend fun postProperty(
        advertType: MultipartBody.Part,
        bathrooms: Int,
        bedrooms: Int,
        city: MultipartBody.Part,
        country: MultipartBody.Part,
        coverPhoto: MultipartBody.Part,
        description: MultipartBody.Part,
        photo1: MultipartBody.Part,
        photo2: MultipartBody.Part,
        photo3: MultipartBody.Part,
        photo4: MultipartBody.Part,
        plotArea: Int,
        postalCode: MultipartBody.Part,
        price: Int,
        propertyNumber: Int,
        propertyType: MultipartBody.Part,
        streetAddress: MultipartBody.Part,
        title: MultipartBody.Part,
        totalFloors: Int
    ): Flow<ResultWrapper<PropertyResponse>>

    suspend fun getSellerProperties(): Flow<ResultWrapper<List<Property>>>

    suspend fun updateProperty(
        slug: String,
        advertType: MultipartBody.Part? = null,
        bathrooms: Int? = null,
        bedrooms: Int? = null,
        city: String? = null,
        country: MultipartBody.Part? = null,
        coverPhoto: MultipartBody.Part? = null,
        description: String? = null,
        photo1: MultipartBody.Part? = null,
        photo2: MultipartBody.Part? = null,
        photo3: MultipartBody.Part? = null,
        photo4: MultipartBody.Part? = null,
        plotArea: Int? = null,
        postalCode: String? = null,
        price: Int? = null,
        propertyNumber: Int? = null,
        propertyType: MultipartBody.Part? = null,
        streetAddress: String? = null,
        title: String? = null,
        totalFloors: Int? = null,
        user: Int
    ): Flow<ResultWrapper<Property>>

    suspend fun deleteProperty(
        slug: String
    ): Flow<ResultWrapper<Property>>

    suspend fun postPropertyListing(
        property: String
    ): Flow<ResultWrapper<PropertyListing>>

    suspend fun getAgentPropertyListing(): Flow<ResultWrapper<List<PropertyListing>>>

    suspend fun getSellerPropertyListing(): Flow<ResultWrapper<List<PropertyListing>>>

    suspend fun getAllPropertyListing(): Flow<ResultWrapper<List<PropertyListing>>>

    suspend fun getPropertyListingDetails(id: Int): Flow<ResultWrapper<PropertyListing>>

    suspend fun deletePropertyListing(id: Int): Flow<ResultWrapper<PropertyListing>>
}