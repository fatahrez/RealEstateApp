package com.example.guryihii.model.mapper

import com.example.guryihii.model.domain.Property
import com.example.guryihii.model.network.RealEstateProperty
import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Inject

class PropertyMapper @Inject constructor() {

    fun buildFrom(realEstateProperty: RealEstateProperty): Property{
        return Property(
            id = realEstateProperty.id,
            profile_photo = realEstateProperty.profile_photo,
            title = realEstateProperty.title,
            price = BigDecimal(realEstateProperty.price).setScale(2,RoundingMode.HALF_UP),
            street_address = realEstateProperty.street_address,
            city = realEstateProperty.city,
            country = realEstateProperty.country,
            slug = realEstateProperty.slug
        )
    }
}

