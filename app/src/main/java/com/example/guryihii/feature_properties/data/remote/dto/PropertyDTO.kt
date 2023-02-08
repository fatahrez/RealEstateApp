package com.example.guryihii.feature_properties.data.remote.dto

import com.example.guryihii.feature_properties.domain.model.Property
import com.google.gson.annotations.SerializedName

data class PropertyDTO (
    @SerializedName("advert_type")
    val advertType: String,
    val bathrooms: String,
    val bedrooms: Int,
    val city: String,
    val country: String,
    @SerializedName("cover_photo")
    val coverPhoto: String,
    val description: String,
    val id: Int,
    val photo1: String,
    val photo2: String,
    val photo3: String,
    val photo4: String,
    @SerializedName("plot_area")
    val plotArea: String,
    @SerializedName("postal_code")
    val postalCode: String,
    val price: String,
    @SerializedName("profile_photo")
    val profilePhoto: String,
    @SerializedName("property_number")
    val propertyNumber: Int,
    @SerializedName("property_type")
    val propertyType: String,
    @SerializedName("published_status")
    val publishedStatus: Boolean,
    @SerializedName("ref_code")
    val refCode: String,
    val slug: String,
    @SerializedName("street_address")
    val streetAddress: String,
    val title: String,
    @SerializedName("total_floors")
    val totalFloors: Int,
    val user: Int,
    val views: Int
) {
    fun toProperty(): Property {
        return Property(
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