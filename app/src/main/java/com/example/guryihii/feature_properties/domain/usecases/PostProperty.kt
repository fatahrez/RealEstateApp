package com.example.guryihii.feature_properties.domain.usecases

import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.feature_properties.domain.model.Property
import com.example.guryihii.feature_properties.domain.repository.PropertyRepository
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

class PostProperty(
    private val propertyRepository: PropertyRepository
) {

    suspend operator fun invoke(
        advertType: String,
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
        propertyType: String,
        streetAddress: String,
        title: String,
        totalFloors: Int
    ): Flow<ResultWrapper<Property>> {
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