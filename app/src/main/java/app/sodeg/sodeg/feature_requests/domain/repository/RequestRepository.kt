package app.sodeg.sodeg.feature_requests.domain.repository

import app.sodeg.sodeg.core.util.ResultWrapper
import app.sodeg.sodeg.feature_requests.domain.model.RequestProperty
import app.sodeg.sodeg.feature_requests.domain.model.RequestPropertyResponse
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

    suspend fun getRequestPropertyDetails(id: Int): Flow<ResultWrapper<RequestProperty>>
}