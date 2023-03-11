package app.sodeg.sodeg.feature_requests.domain.usecases

import app.sodeg.sodeg.core.util.ResultWrapper
import app.sodeg.sodeg.feature_requests.domain.model.RequestProperty
import app.sodeg.sodeg.feature_requests.domain.repository.RequestRepository
import kotlinx.coroutines.flow.Flow

class GetAllRequests(
    private val requestRepository: RequestRepository
) {

    suspend operator fun invoke(): Flow<ResultWrapper<List<RequestProperty>>> {
        return requestRepository.getAllRequests()
    }

}