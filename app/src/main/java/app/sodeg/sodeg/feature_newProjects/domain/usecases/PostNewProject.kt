package app.sodeg.sodeg.feature_newProjects.domain.usecases

import app.sodeg.sodeg.core.util.ResultWrapper
import app.sodeg.sodeg.feature_newProjects.domain.model.NewProject
import app.sodeg.sodeg.feature_newProjects.domain.repository.NewProjectRepository
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

class PostNewProject(
    private val repository: NewProjectRepository
) {
    suspend operator fun invoke(
        name: MultipartBody.Part,
        location: MultipartBody.Part,
        description: MultipartBody.Part,
        price: MultipartBody.Part,
        bedrooms: Int,
        bathrooms: Int,
        squareFeet: Int,
        country: MultipartBody.Part,
        city: MultipartBody.Part,
        constructionStatus: MultipartBody.Part,
        completionDate: MultipartBody.Part,
        propertyType: MultipartBody.Part,
        coverPhoto: MultipartBody.Part,
        photo1: MultipartBody.Part,
        photo2: MultipartBody.Part
    ) : Flow<ResultWrapper<NewProject>> {
        return repository.postNewProject(
            name,
            location,
            description,
            price,
            bedrooms,
            bathrooms,
            squareFeet,
            country,
            city,
            constructionStatus,
            completionDate,
            propertyType,
            coverPhoto,
            photo1,
            photo2
        )
    }

}