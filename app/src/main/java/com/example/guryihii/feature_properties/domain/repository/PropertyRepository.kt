package com.example.guryihii.feature_properties.domain.repository

import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.feature_properties.domain.model.Property
import kotlinx.coroutines.flow.Flow

interface PropertyRepository {
    suspend fun getAllProperties(): Flow<ResultWrapper<List<Property>>>
}