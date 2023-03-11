package app.sodeg.sodeg.feature_properties.data.remote.dto

import app.sodeg.sodeg.feature_properties.domain.model.response.PropertyResponse
import com.google.gson.annotations.SerializedName

data class PropertyResponseDTO(
    @SerializedName("advert_type")
    val advertType: String,
    val bathrooms: String,
    val bedrooms: Int,
    val city: String,
    val country: String,
    @SerializedName("cover_photo")
    val coverPhoto: String,
    @SerializedName("created_at")
    val createdAt: String,
    val deleted: Boolean,
    val description: String,
    val id: Int,
    @SerializedName("is_active")
    val isActive: Boolean,
    val photo1: String,
    val photo2: String,
    val photo3: String,
    val photo4: String,
    @SerializedName("plot_area")
    val plotArea: String,
    @SerializedName("postal_code")
    val postalCode: String,
    val price: String,
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
    fun toPropertyResponse(): PropertyResponse {
        return PropertyResponse(
            advertType = advertType,
            bathrooms = bathrooms,
            bedrooms = bedrooms,
            city = city,
            country = country,
            coverPhoto = coverPhoto,
            createdAt = createdAt,
            deleted = deleted,
            description = description,
            id = id,
            isActive = isActive,
            photo1 = photo1,
            photo2 = photo2,
            photo3 = photo3,
            photo4 = photo4,
            plotArea = plotArea,
            postalCode = postalCode,
            price = price,
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
