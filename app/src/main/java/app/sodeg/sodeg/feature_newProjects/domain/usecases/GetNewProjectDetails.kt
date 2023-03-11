package app.sodeg.sodeg.feature_newProjects.domain.usecases

import app.sodeg.sodeg.core.util.ResultWrapper
import app.sodeg.sodeg.feature_newProjects.domain.model.NewProject
import app.sodeg.sodeg.feature_newProjects.domain.repository.NewProjectRepository
import kotlinx.coroutines.flow.Flow

class GetNewProjectDetails(
    private val repository: NewProjectRepository
) {

    suspend operator fun invoke(slug: String): Flow<ResultWrapper<NewProject>> {
        return repository.getNewProjectDetails(slug)
    }

}