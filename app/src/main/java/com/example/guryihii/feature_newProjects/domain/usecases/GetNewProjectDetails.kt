package com.example.guryihii.feature_newProjects.domain.usecases

import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.feature_newProjects.domain.model.NewProject
import com.example.guryihii.feature_newProjects.domain.repository.NewProjectRepository
import kotlinx.coroutines.flow.Flow

class GetNewProjectDetails(
    private val repository: NewProjectRepository
) {

    suspend operator fun invoke(slug: String): Flow<ResultWrapper<NewProject>> {
        return repository.getNewProjectDetails(slug)
    }

}