package app.sodeg.sodeg.feature_newProjects.domain.repository


import app.sodeg.sodeg.core.util.ResultWrapper
import app.sodeg.sodeg.feature_newProjects.domain.model.NewProject
import kotlinx.coroutines.flow.Flow

interface NewProjectRepository {

    suspend fun getAllNewProjects(): Flow<ResultWrapper<List<NewProject>>>

    suspend fun getNewProjectDetails(slug: String): Flow<ResultWrapper<NewProject>>

}