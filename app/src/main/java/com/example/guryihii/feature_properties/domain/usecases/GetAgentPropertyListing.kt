package com.example.guryihii.feature_properties.domain.usecases

import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.feature_properties.domain.model.PropertyListing
import com.example.guryihii.feature_properties.domain.repository.PropertyRepository
import kotlinx.coroutines.flow.Flow

class GetAgentPropertyListing(
    private val repository: PropertyRepository
) {

    suspend operator fun invoke(): Flow<ResultWrapper<List<PropertyListing>>> {
        return repository.getAgentPropertyListing()
    }

}