package com.example.guryihii.feature_requests.data.repository

import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.core.util.safeApiCall
import com.example.guryihii.feature_requests.data.remote.RequestAPI
import com.example.guryihii.feature_requests.domain.model.RequestProperty
import com.example.guryihii.feature_requests.domain.model.RequestPropertyResponse
import com.example.guryihii.feature_requests.domain.repository.RequestRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class RequestRepositoryImpl(
    private val api: RequestAPI,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): RequestRepository {
    override suspend fun getAllRequests(): Flow<ResultWrapper<List<RequestProperty>>>
    = safeApiCall(ioDispatcher) {
        api.getAllRequests().map { it.toRequestProperty() }
    }

    override suspend fun postRequest(
        name: String,
        email: String,
        phoneNumber: String,
        subject: String,
        message: String
    ): Flow<ResultWrapper<RequestPropertyResponse>> = safeApiCall(ioDispatcher){
        api.postRequest(name, email, phoneNumber, subject, message).toRequestPropertyResponse()
    }

    override suspend fun getRequestPropertyDetails(id: Int): Flow<ResultWrapper<RequestProperty>>
    = safeApiCall(ioDispatcher) {
        api.getRequestDetails(id).toRequestProperty()
    }


}