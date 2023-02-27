package com.example.guryihii.feature_requests.domain.usecases

import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.feature_requests.domain.model.RequestProperty
import com.example.guryihii.feature_requests.domain.repository.RequestRepository
import kotlinx.coroutines.flow.Flow

class PostRequest(
    private val requestRepository: RequestRepository
) {

    suspend operator fun invoke(
        name: String,
        email: String,
        phoneNumber: String,
        subject: String,
        message: String
    ) : Flow<ResultWrapper<RequestProperty>> {
        return requestRepository.postRequest(name, email, phoneNumber, subject, message)
    }

}