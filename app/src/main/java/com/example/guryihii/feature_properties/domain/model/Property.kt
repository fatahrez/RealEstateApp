package com.example.guryihii.feature_properties.domain.model

import com.example.guryihii.feature_properties.data.remote.dto.PropertyDTO

data class Property(
    val advertType: String,
    val bathrooms: String,
    val bedrooms: Int,
    val city: String,
    val country: String,
    val coverPhoto: String,
    val description: String,
    val id: Int,
    val photo1: String,
    val photo2: String,
    val photo3: String,
    val photo4: String,
    val plotArea: String,
    val postalCode: String,
    val price: String,
    val profilePhoto: String,
    val propertyNumber: Int,
    val propertyType: String,
    val publishedStatus: Boolean,
    val refCode: String,
    val slug: String,
    val streetAddress: String,
    val title: String,
    val totalFloors: Int,
    val user: Int,
    val views: Int
) {
    fun toPropertyDTO(): PropertyDTO {
        return PropertyDTO(
            advertType = advertType,
            bathrooms = bathrooms,
            bedrooms = bedrooms,
            city = city,
            country = country,
            coverPhoto = coverPhoto,
            description = description,
            id = id,
            photo1 = photo1,
            photo2 = photo2,
            photo3 = photo3,
            photo4 = photo4,
            plotArea = plotArea,
            postalCode = postalCode,
            price = price,
            profilePhoto = profilePhoto,
            propertyNumber = propertyNumber,
            propertyType = propertyType,
            publishedStatus = publishedStatus,
            refCode = refCode,
            slug = slug,
            streetAddress = streetAddress,
            title = title,
            totalFloors = totalFloors,
            user = user,
            views = views
        )
    }
}