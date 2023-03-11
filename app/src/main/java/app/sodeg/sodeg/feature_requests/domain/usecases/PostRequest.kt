package app.sodeg.sodeg.feature_requests.domain.usecases

import app.sodeg.sodeg.core.util.ResultWrapper
import app.sodeg.sodeg.feature_requests.domain.model.RequestPropertyResponse
import app.sodeg.sodeg.feature_requests.domain.repository.RequestRepository
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
    ) : Flow<ResultWrapper<RequestPropertyResponse>> {
        return requestRepository.postRequest(name, email, phoneNumber, subject, message)
    }

}