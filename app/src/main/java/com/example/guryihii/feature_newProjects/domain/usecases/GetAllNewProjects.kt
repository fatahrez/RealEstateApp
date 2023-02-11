package com.example.guryihii.feature_newProjects.domain.usecases

import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.feature_newProjects.domain.model.NewProject
import com.example.guryihii.feature_newProjects.domain.repository.NewProjectRepository
import kotlinx.coroutines.flow.Flow

class GetAllNewProjects(
    private val repository: NewProjectRepository
) {
    suspend operator fun invoke(): Flow<ResultWrapper<List<NewProject>>> {
        return repository.getAllNewProjects()
    }
}