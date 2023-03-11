package app.sodeg.sodeg.feature_newProjects.domain.usecases


import app.sodeg.sodeg.core.util.ResultWrapper
import app.sodeg.sodeg.feature_newProjects.domain.model.NewProject
import app.sodeg.sodeg.feature_newProjects.domain.repository.NewProjectRepository
import kotlinx.coroutines.flow.Flow

class GetAllNewProjects(
    private val repository: NewProjectRepository
) {
    suspend operator fun invoke(): Flow<ResultWrapper<List<NewProject>>> {
        return repository.getAllNewProjects()
    }
}