package com.example.guryihii.feature_newProjects.data.remote

import com.example.guryihii.feature_newProjects.data.remote.dto.NewProjectDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface NewProjectAPI {

    @GET("properties/new_projects/all/")
    suspend fun getAllNewProjectsDTO(): List<NewProjectDTO>

    @GET("properties/new_projects/details/{slug}")
    suspend fun getNewProjectDetailsDTO(@Path("slug") slug: String): NewProjectDTO

}