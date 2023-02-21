package com.example.guryihii.feature_properties.domain.usecases

import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.feature_properties.domain.model.Property
import com.example.guryihii.feature_properties.domain.repository.PropertyRepository
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

class UpdateProperty(
    private val repository: PropertyRepository
) {

    suspend operator fun invoke(
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
    ): Flow<ResultWrapper<Property>> {
        return repository.updateProperty(
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
        )
    }

}