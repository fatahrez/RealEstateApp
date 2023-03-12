package app.sodeg.sodeg.feature_newProjects.domain.repository


import app.sodeg.sodeg.core.util.ResultWrapper
import app.sodeg.sodeg.feature_newProjects.domain.model.NewProject
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

interface NewProjectRepository {

    suspend fun getAllNewProjects(): Flow<ResultWrapper<List<NewProject>>>

    suspend fun getNewProjectDetails(slug: String): Flow<ResultWrapper<NewProject>>

    suspend fun postNewProject(
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
    ): Flow<ResultWrapper<NewProject>>

    suspend fun updateNewProject(
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
        coverPhoto: MultipartBody.Part,
        photo1: MultipartBody.Part,
        photo2: MultipartBody.Part
    ): Flow<ResultWrapper<NewProject>>

    suspend fun deleteNewProject(
        slug: String
    ): Flow<ResultWrapper<String>>

}