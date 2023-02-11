package com.example.guryihii.feature_newProjects.presentation.new_project_details

import com.example.guryihii.feature_newProjects.domain.model.NewProject

data class NewProjectDetailsState(
    val newProject: NewProject? = null,
    val isLoading: Boolean = false
)
