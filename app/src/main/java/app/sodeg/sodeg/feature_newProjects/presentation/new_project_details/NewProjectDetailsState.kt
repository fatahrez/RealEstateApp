package app.sodeg.sodeg.feature_newProjects.presentation.new_project_details

import app.sodeg.sodeg.feature_newProjects.domain.model.NewProject


data class NewProjectDetailsState(
    val newProject: NewProject? = null,
    val isLoading: Boolean = false
)
