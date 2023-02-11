package com.example.guryihii.feature_newProjects.domain.model

data class NewProject(
    val id: Int,
    val user: Int,
    val profilePhoto: String,
    val name: String,
    val slug: String,
    val location: String,
    val refCode: String,
    val description: String,
    val country: String,
    val city: String,
    val price: String,
    val squareFeet: Int,
    val bedrooms: Int,
    val bathrooms: Int,
    val propertyType: String,
    val constructionStatus: String,
    val completionDate: String,
    val coverPhoto: String,
    val photo1: String,
    val photo2: String,
    val publishedStatus: Boolean,
    val views: Int
)
