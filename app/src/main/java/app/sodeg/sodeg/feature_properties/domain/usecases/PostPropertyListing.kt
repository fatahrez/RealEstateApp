package app.sodeg.sodeg.feature_properties.domain.usecases

import app.sodeg.sodeg.core.util.ResultWrapper
import app.sodeg.sodeg.feature_properties.domain.model.PropertyListing
import app.sodeg.sodeg.feature_properties.domain.repository.PropertyRepository
import kotlinx.coroutines.flow.Flow

class PostPropertyListing(
    private val propertyRepository: PropertyRepository
) {

    suspend operator fun invoke(property: String): Flow<ResultWrapper<PropertyListing>> {
        return propertyRepository.postPropertyListing(property)
    }

}