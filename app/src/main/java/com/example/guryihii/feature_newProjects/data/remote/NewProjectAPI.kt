package com.example.guryihii.feature_newProjects.data.remote

import com.example.guryihii.feature_newProjects.data.remote.dto.NewProjectDTO
import retrofit2.http.GET

interface NewProjectAPI {

    @GET("properties/new_projects/all/")
    fun getAllNewProjectsDTO(): List<NewProjectDTO>
}