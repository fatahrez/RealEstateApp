package com.example.guryihii.feature_properties.domain.repository

import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.feature_properties.domain.model.Property
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

interface PropertyRepository {
    suspend fun getAllProperties(): Flow<ResultWrapper<List<Property>>>

    suspend fun getPropertyDetails(slug: String): Flow<ResultWrapper<Property>>

    suspend fun postProperty(
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
    ): Flow<ResultWrapper<Property>>
}