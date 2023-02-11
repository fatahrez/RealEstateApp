package com.example.guryihii.feature_newProjects.data.repository

import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.core.util.safeApiCall
import com.example.guryihii.feature_newProjects.data.remote.NewProjectAPI
import com.example.guryihii.feature_newProjects.domain.model.NewProject
import com.example.guryihii.feature_newProjects.domain.repository.NewProjectRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

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

}