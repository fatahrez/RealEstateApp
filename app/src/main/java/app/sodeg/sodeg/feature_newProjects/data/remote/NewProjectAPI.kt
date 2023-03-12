package app.sodeg.sodeg.feature_newProjects.data.remote

import app.sodeg.sodeg.feature_newProjects.data.remote.dto.NewProjectDTO
import okhttp3.MultipartBody
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface NewProjectAPI {

    @GET("properties/new_projects/all/")
    suspend fun getAllNewProjectsDTO(): List<NewProjectDTO>

    @GET("properties/new_projects/details/{slug}")
    suspend fun getNewProjectDetailsDTO(@Path("slug") slug: String): NewProjectDTO

    @Multipart
    @POST("properties/new_projects/create/")
    suspend fun postNewProject(
        @Part name: MultipartBody.Part,
        @Part location: MultipartBody.Part,
        @Part description: MultipartBody.Part,
        @Part price: MultipartBody.Part,
        @Part("bedrooms") bedrooms: Int,
        @Part("bathrooms") bathrooms: Int,
        @Part("square_feet") squareFeet: Int,
        @Part country: MultipartBody.Part,
        @Part city: MultipartBody.Part,
        @Part constructionStatus: MultipartBody.Part,
        @Part completionDate: MultipartBody.Part,
        @Part propertyType: MultipartBody.Part,
        @Part coverPhoto: MultipartBody.Part,
        @Part photo1: MultipartBody.Part,
        @Part photo2: MultipartBody.Part
    ): NewProjectDTO

    @Multipart
    @POST("properties/new_projects/update/{slug}/")
    suspend fun updateNewProject(
        @Path("slug") slug: String,
        @Part name: MultipartBody.Part,
        @Part location: MultipartBody.Part,
        @Part description: MultipartBody.Part,
        @Part price: MultipartBody.Part,
        @Part("bedrooms") bedrooms: Int,
        @Part("bathrooms") bathrooms: Int,
        @Part("square_feet") squareFeet: Int,
        @Part country: MultipartBody.Part,
        @Part city: MultipartBody.Part,
        @Part constructionStatus: MultipartBody.Part,
        @Part completionDate: MultipartBody.Part,
        @Part propertyType: MultipartBody.Part,
        @Part coverPhoto: MultipartBody.Part,
        @Part photo1: MultipartBody.Part,
        @Part photo2: MultipartBody.Part
    ): NewProjectDTO

    @DELETE("properties/new_projects/delete/{slug}/")
    suspend fun deleteNewProject(
        @Path("slug") slug: String
    ): String
}