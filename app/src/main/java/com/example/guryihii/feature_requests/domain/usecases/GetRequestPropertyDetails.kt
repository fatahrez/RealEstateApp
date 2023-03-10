package com.example.guryihii.feature_requests.domain.usecases

import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.feature_requests.domain.model.RequestProperty
import com.example.guryihii.feature_requests.domain.repository.RequestRepository
import kotlinx.coroutines.flow.Flow

class GetRequestPropertyDetails(
    private val repository: RequestRepository
) {

    suspend operator fun invoke(id: Int): Flow<ResultWrapper<RequestProperty>> {
        return repository.getRequestPropertyDetails(id)
    }

}