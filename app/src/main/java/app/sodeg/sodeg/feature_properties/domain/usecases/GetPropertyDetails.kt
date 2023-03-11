package app.sodeg.sodeg.feature_properties.domain.usecases

import app.sodeg.sodeg.core.util.ResultWrapper
import app.sodeg.sodeg.feature_properties.domain.model.Property
import app.sodeg.sodeg.feature_properties.domain.repository.PropertyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPropertyDetails @Inject constructor(
    private val repository: PropertyRepository
) {

    suspend operator fun invoke(slug: String): Flow<ResultWrapper<Property>> {
        if (slug.isBlank()) {
            return flow {  }
        }
        return repository.getPropertyDetails(slug)
    }

}