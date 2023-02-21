package com.example.guryihii.feature_properties.domain.repository

import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.feature_properties.domain.model.Property
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface PropertyRepository {
    suspend fun getAllProperties(): Flow<ResultWrapper<List<Property>>>

    suspend fun getPropertyDetails(slug: String): Flow<ResultWrapper<Property>>

    suspend fun postProperty(
        advertType: MultipartBody.Part,
        bathrooms: Int,
        bedrooms: Int,
        city: String,
        country: MultipartBody.Part,
        coverPhoto: MultipartBody.Part,
        description: String,
        photo1: MultipartBody.Part,
        photo2: MultipartBody.Part,
        photo3: MultipartBody.Part,
        photo4: MultipartBody.Part,
        plotArea: Int,
        postalCode: String,
        price: Int,
        propertyNumber: Int,
        propertyType: MultipartBody.Part,
        streetAddress: String,
        title: String,
        totalFloors: Int
    ): Flow<ResultWrapper<Property>>

    suspend fun getSellerProperties(): Flow<ResultWrapper<List<Property>>>

    suspend fun updateProperty(
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
        totalFloors: Int? = null
    ): Flow<ResultWrapper<Property>>
}