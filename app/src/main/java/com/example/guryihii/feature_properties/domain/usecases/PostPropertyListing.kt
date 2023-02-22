package com.example.guryihii.feature_properties.domain.usecases

import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.feature_properties.domain.model.PropertyListing
import com.example.guryihii.feature_properties.domain.repository.PropertyRepository
import kotlinx.coroutines.flow.Flow

class PostPropertyListing(
    private val propertyRepository: PropertyRepository
) {

    suspend operator fun invoke(property: String): Flow<ResultWrapper<PropertyListing>> {
        return propertyRepository.postPropertyListing(property)
    }

}