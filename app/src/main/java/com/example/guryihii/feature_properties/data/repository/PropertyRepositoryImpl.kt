package com.example.guryihii.feature_properties.data.repository

import android.util.Log
import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.core.util.safeApiCall
import com.example.guryihii.feature_properties.data.remote.PropertyAPI
import com.example.guryihii.feature_properties.domain.model.Property
import com.example.guryihii.feature_properties.domain.repository.PropertyRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PropertyRepositoryImpl @Inject constructor(
    private val apiService: PropertyAPI,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): PropertyRepository {
    override suspend fun getAllProperties():
            Flow<ResultWrapper<List<Property>>> = safeApiCall(ioDispatcher) {
        apiService.getAllProperties().results.map {
            it.toProperty()
        }
    }

    override suspend fun getPropertyDetails(slug: String): Flow<ResultWrapper<Property>> =
        safeApiCall(ioDispatcher) {
        apiService.getPropertyDetail(slug).toProperty()
    }

    override suspend fun postProperty(property: Property): Flow<ResultWrapper<Property>> =
        safeApiCall(ioDispatcher){
        apiService.postProperty(property.toPropertyDTO()).toProperty()
    }
}