package com.example.guryihii.feature_newProjects.domain.repository

import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.feature_newProjects.domain.model.NewProject
import kotlinx.coroutines.flow.Flow

interface NewProjectRepository {

    suspend fun getAllNewProjects(): Flow<ResultWrapper<List<NewProject>>>

}