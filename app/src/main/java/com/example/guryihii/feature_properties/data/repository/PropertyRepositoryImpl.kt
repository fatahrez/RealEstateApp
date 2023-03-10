package com.example.guryihii.feature_properties.data.repository

import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.core.util.safeApiCall
import com.example.guryihii.feature_properties.data.remote.PropertyAPI
import com.example.guryihii.feature_properties.domain.model.Property
import com.example.guryihii.feature_properties.domain.model.PropertyListing
import com.example.guryihii.feature_properties.domain.repository.PropertyRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Part
import javax.inject.Inject

class PropertyRepositoryImpl @Inject constructor(
    private val apiService: PropertyAPI,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): PropertyRepository {
    override suspend fun getAllProperties():
            Flow<ResultWrapper<List<Property>>> = safeApiCall(ioDispatcher) {
        apiService.getAllProperties().results.map {
            it.toProperty()
        }
    }

    override suspend fun getPropertyDetails(slug: String): Flow<ResultWrapper<Property>> =
        safeApiCall(ioDispatcher) {
        apiService.getPropertyDetail(slug).toProperty()
    }

    override suspend fun postProperty(
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
    ): Flow<ResultWrapper<Property>> =
        safeApiCall(ioDispatcher){
        apiService.postProperty(
            advertType,
            bathrooms,
            bedrooms,
            city,
            country,
            coverPhoto,
            description,
            photo1,
            photo2,
            photo3,
            photo4,
            plotArea,
            postalCode,
            price,
            propertyNumber,
            propertyType,
            streetAddress,
            title,
            totalFloors
        ).toProperty()
    }

    override suspend fun getSellerProperties(): Flow<ResultWrapper<List<Property>>>
    = safeApiCall(ioDispatcher) {
        apiService.getSellerProperties().results.map {
            it.toProperty()
        }
    }

    override suspend fun updateProperty(
        slug: String,
        advertType: MultipartBody.Part?,
        bathrooms: Int?,
        bedrooms: Int?,
        city: String?,
        country: MultipartBody.Part?,
        coverPhoto: MultipartBody.Part?,
        description: String?,
        photo1: MultipartBody.Part?,
        photo2: MultipartBody.Part?,
        photo3: MultipartBody.Part?,
        photo4: MultipartBody.Part?,
        plotArea: Int?,
        postalCode: String?,
        price: Int?,
        propertyNumber: Int?,
        propertyType: MultipartBody.Part?,
        streetAddress: String?,
        title: String?,
        totalFloors: Int?,
        user: Int
    ): Flow<ResultWrapper<Property>> = safeApiCall(ioDispatcher) {
        apiService.updateProperty(
            slug,
            advertType,
            bathrooms,
            bedrooms,
            city,
            country,
            coverPhoto,
            description,
            photo1,
            photo2,
            photo3,
            photo4,
            plotArea,
            postalCode,
            price,
            propertyNumber,
            propertyType,
            streetAddress,
            title,
            totalFloors,
            user
        ).toProperty()
    }

    override suspend fun deleteProperty(slug: String): Flow<ResultWrapper<Property>> =
        safeApiCall(ioDispatcher) {
        apiService.deleteProperty(slug).toProperty()
    }

    override suspend fun postPropertyListing(property: String): Flow<ResultWrapper<PropertyListing>>
    = safeApiCall(ioDispatcher){
        apiService.postPropertyListing(property).toPropertyListing()
    }

    override suspend fun getAgentPropertyListing(): Flow<ResultWrapper<List<PropertyListing>>>
    = safeApiCall(ioDispatcher){
        apiService.getAgentPropertyListing().map {
            it.toPropertyListing()
        }
    }

    override suspend fun getSellerPropertyListing(): Flow<ResultWrapper<List<PropertyListing>>>
    = safeApiCall(ioDispatcher){
        apiService.getSellerPropertyListing().map {
            it.toPropertyListing()
        }
    }

    override suspend fun getAllPropertyListing(): Flow<ResultWrapper<List<PropertyListing>>>
    = safeApiCall(ioDispatcher){
        apiService.getAllPropertyListing().map {
            it.toPropertyListing()
        }
    }

    override suspend fun getPropertyListingDetails(id: Int): Flow<ResultWrapper<PropertyListing>>
    = safeApiCall(ioDispatcher){
        apiService.getPropertyListingDetails(id).toPropertyListing()
    }

    override suspend fun deletePropertyListing(id: Int): Flow<ResultWrapper<PropertyListing>>
    = safeApiCall(ioDispatcher){
        apiService.deletePropertyListing(id).toPropertyListing()
    }
}