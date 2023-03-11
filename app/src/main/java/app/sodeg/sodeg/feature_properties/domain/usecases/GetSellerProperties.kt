package app.sodeg.sodeg.feature_properties.domain.usecases

import app.sodeg.sodeg.core.util.ResultWrapper
import app.sodeg.sodeg.feature_properties.domain.model.Property
import app.sodeg.sodeg.feature_properties.domain.repository.PropertyRepository
import kotlinx.coroutines.flow.Flow

class GetSellerProperties(
    private val repository: PropertyRepository
) {

    suspend operator fun invoke(): Flow<ResultWrapper<List<Property>>> {
        return repository.getSellerProperties()
    }

}