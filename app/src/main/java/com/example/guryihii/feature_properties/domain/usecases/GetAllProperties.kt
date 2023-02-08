package com.example.guryihii.feature_properties.domain.usecases

import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.feature_properties.domain.model.Property
import com.example.guryihii.feature_properties.domain.repository.PropertyRepository
import kotlinx.coroutines.flow.Flow

class GetAllProperties(
    private val repository: PropertyRepository
) {
    operator fun invoke(): Flow<ResultWrapper<List<Property>>> {
        return repository.getAllProperties()
    }
}