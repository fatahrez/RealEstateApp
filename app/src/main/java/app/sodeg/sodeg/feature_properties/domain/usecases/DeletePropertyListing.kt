package app.sodeg.sodeg.feature_properties.domain.usecases

import app.sodeg.sodeg.core.util.ResultWrapper
import app.sodeg.sodeg.feature_properties.domain.model.PropertyListing
import app.sodeg.sodeg.feature_properties.domain.repository.PropertyRepository
import kotlinx.coroutines.flow.Flow

class DeletePropertyListing(
    private val repository: PropertyRepository
) {

    suspend operator fun invoke(id: Int): Flow<ResultWrapper<PropertyListing>> {
        return repository.deletePropertyListing(id)
    }

}