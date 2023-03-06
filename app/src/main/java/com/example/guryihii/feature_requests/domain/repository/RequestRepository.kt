package com.example.guryihii.feature_requests.domain.repository

import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.feature_requests.domain.model.RequestProperty
import com.example.guryihii.feature_requests.domain.model.RequestPropertyResponse
import kotlinx.coroutines.flow.Flow

interface RequestRepository {

    suspend fun getAllRequests(): Flow<ResultWrapper<List<RequestProperty>>>

    suspend fun postRequest(
        name: String,
        email: String,
        phoneNumber: String,
        subject: String,
        message: String
    ): Flow<ResultWrapper<RequestPropertyResponse>>

}