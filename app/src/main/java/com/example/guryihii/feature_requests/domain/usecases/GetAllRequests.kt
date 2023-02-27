package com.example.guryihii.feature_requests.domain.usecases

import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.feature_requests.domain.model.RequestProperty
import com.example.guryihii.feature_requests.domain.repository.RequestRepository
import kotlinx.coroutines.flow.Flow

class GetAllRequests(
    private val requestRepository: RequestRepository
) {

    suspend operator fun invoke(): Flow<ResultWrapper<List<RequestProperty>>> {
        return requestRepository.getAllRequests()
    }

}