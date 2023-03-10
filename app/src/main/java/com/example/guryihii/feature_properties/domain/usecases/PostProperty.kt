package com.example.guryihii.feature_properties.domain.usecases

import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.feature_properties.domain.model.Property
import com.example.guryihii.feature_properties.domain.model.response.PropertyResponse
import com.example.guryihii.feature_properties.domain.repository.PropertyRepository
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

class PostProperty(
    private val propertyRepository: PropertyRepository
) {

    suspend operator fun invoke(
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
    ): Flow<ResultWrapper<PropertyResponse>> {
        return propertyRepository.postProperty(
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
        )
    }

}