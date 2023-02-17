package com.example.guryihii.feature_properties.domain.usecases

import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.feature_properties.domain.model.Property
import com.example.guryihii.feature_properties.domain.repository.PropertyRepository
import kotlinx.coroutines.flow.Flow

class PostProperty(
    private val propertyRepository: PropertyRepository
) {

    suspend operator fun invoke(property: Property): Flow<ResultWrapper<Property>> {
        return propertyRepository.postProperty(property)
    }

}