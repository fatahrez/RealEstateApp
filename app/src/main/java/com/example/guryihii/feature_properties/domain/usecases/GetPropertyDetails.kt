package com.example.guryihii.feature_properties.domain.usecases

import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.feature_properties.domain.model.Property
import com.example.guryihii.feature_properties.domain.repository.PropertyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPropertyDetails @Inject constructor(
    private val repository: PropertyRepository
) {

    suspend operator fun invoke(slug: String): Flow<ResultWrapper<Property>> {
        if (slug.isBlank()) {
            return flow {  }
        }
        return repository.getPropertyDetails(slug)
    }

}