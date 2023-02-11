package com.example.guryihii.feature_newProjects.data.remote.dto

import com.example.guryihii.feature_newProjects.domain.model.NewProject
import com.google.gson.annotations.SerializedName

data class NewProjectDTO(
    val id: Int,
    val user: Int,
    @SerializedName("profile_photo")
    val profilePhoto: String,
    val name: String,
    val slug: String,
    val location: String,
    @SerializedName("ref_code")
    val refCode: String,
    val description: String,
    val country: String,
    val city: String,
    val price: String,
    @SerializedName("square_feet")
    val squareFeet: Int,
    val bedrooms: Int,
    val bathrooms: Int,
    @SerializedName("property_type")
    val propertyType: String,
    @SerializedName("construction_status")
    val constructionStatus: String,
    @SerializedName("completion_date")
    val completionDate: String,
    @SerializedName("cover_photo")
    val coverPhoto: String,
    val photo1: String,
    val photo2: String,
    @SerializedName("published_status")
    val publishedStatus: Boolean,
    val views: Int
) {
    fun toNewProject(): NewProject {
        return NewProject(
            id = id,
            user = user,
            profilePhoto = profilePhoto,
            name = name,
            slug = slug,
            location = location,
            refCode = refCode,
            description = description,
            country = country,
            city = city,
            price = price,
            squareFeet = squareFeet,
            bedrooms = bedrooms,
            bathrooms = bathrooms,
            propertyType = propertyType,
            constructionStatus = constructionStatus,
            completionDate = completionDate,
            coverPhoto = coverPhoto,
            photo1 = photo1,
            photo2 = photo2,
            publishedStatus = publishedStatus,
            views = views
        )
    }
}
