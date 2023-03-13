package app.sodeg.sodeg.feature_newProjects.data.repository


import app.sodeg.sodeg.core.util.ResultWrapper
import app.sodeg.sodeg.core.util.safeApiCall
import app.sodeg.sodeg.feature_newProjects.data.remote.NewProjectAPI
import app.sodeg.sodeg.feature_newProjects.domain.model.NewProject
import app.sodeg.sodeg.feature_newProjects.domain.repository.NewProjectRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

class NewProjectRepositoryImpl(
    private val apiService: NewProjectAPI,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): NewProjectRepository {
    override suspend fun getAllNewProjects():
            Flow<ResultWrapper<List<NewProject>>> = safeApiCall(ioDispatcher) {
        apiService.getAllNewProjectsDTO().map {
            it.toNewProject()
        }
    }

    override suspend fun getNewProjectDetails(slug: String):
            Flow<ResultWrapper<NewProject>> = safeApiCall(ioDispatcher) {
        apiService.getNewProjectDetailsDTO(slug).toNewProject()
    }

    override suspend fun postNewProject(
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
    ): Flow<ResultWrapper<NewProject>> = safeApiCall(ioDispatcher){
        apiService.postNewProject(
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
        ).toNewProject()
    }

    override suspend fun updateNewProject(
        slug: String,
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
        coverPhoto: MultipartBody.Part?,
        photo1: MultipartBody.Part?,
        photo2: MultipartBody.Part?,
        user: Int
    ): Flow<ResultWrapper<NewProject>> = safeApiCall(ioDispatcher) {
        apiService.updateNewProject(
            slug,
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
            photo2,
            user
        ).toNewProject()
    }

    override suspend fun deleteNewProject(slug: String): Flow<ResultWrapper<String>> =
        safeApiCall(ioDispatcher) {
        apiService.deleteNewProject(slug)
    }

}