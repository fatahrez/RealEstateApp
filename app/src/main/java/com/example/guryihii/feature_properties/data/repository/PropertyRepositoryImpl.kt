package com.example.guryihii.feature_properties.data.repository

import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.core.util.safeApiCall
import com.example.guryihii.feature_properties.data.remote.PropertyAPI
import com.example.guryihii.feature_properties.domain.model.Property
import com.example.guryihii.feature_properties.domain.repository.PropertyRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
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
        advertType: String,
        bathrooms: String,
        bedrooms: Int,
        city: String,
        country: String,
        coverPhoto: MultipartBody.Part,
        description: String,
        photo1: MultipartBody.Part,
        photo2: MultipartBody.Part,
        photo3: MultipartBody.Part,
        photo4: MultipartBody.Part,
        plotArea: String,
        postalCode: String,
        price: String,
        propertyNumber: String,
        propertyType: String,
        streetAddress: String,
        title: String,
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
}