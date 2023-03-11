package app.sodeg.sodeg.feature_requests.domain.usecases

import app.sodeg.sodeg.core.util.ResultWrapper
import app.sodeg.sodeg.feature_requests.domain.model.RequestProperty
import app.sodeg.sodeg.feature_requests.domain.repository.RequestRepository
import kotlinx.coroutines.flow.Flow

class GetRequestPropertyDetails(
    private val repository: RequestRepository
) {

    suspend operator fun invoke(id: Int): Flow<ResultWrapper<RequestProperty>> {
        return repository.getRequestPropertyDetails(id)
    }

}