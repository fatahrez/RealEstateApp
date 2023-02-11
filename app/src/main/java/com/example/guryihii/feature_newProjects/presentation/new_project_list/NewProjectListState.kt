package com.example.guryihii.feature_newProjects.presentation.new_project_list

import com.example.guryihii.feature_newProjects.domain.model.NewProject

data class NewProjectListState(
    val newProjects: List<NewProject> = emptyList(),
    val isLoading: Boolean = false
)
