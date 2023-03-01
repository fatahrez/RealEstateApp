package com.example.guryihii.feature_properties.presentation.property_detail

import coil.load
import com.airbnb.epoxy.TypedEpoxyController
import com.example.garissaestore.epoxy.ViewBindingKotlinModel
import com.example.guryihii.R
import com.example.guryihii.core.util.Constants
import com.example.guryihii.databinding.ListPropertyDetailBinding

class PropertyDetailController():TypedEpoxyController<PropertyDetailState>() {
    override fun buildModels(data: PropertyDetailState?) {
        if (data != null) {
            data.property?.let {
                PropertyEpoxyModel(
                    slug = data!!.property!!.slug,
                    coverPhoto = data.property.coverPhoto,
                    street = it.streetAddress,
                    country = data.property.country,
                    price = data.property.price
                ).id("property_detail").addTo(this)
            }
        }
    }
}

data class PropertyEpoxyModel(
    val slug: String,
    val coverPhoto: String,
    val street: String,
    val country: String,
    val price: String,
): ViewBindingKotlinModel<ListPropertyDetailBinding>(R.layout.list_property_detail){
    override fun ListPropertyDetailBinding.bind() {
        tvApartments.text = slug
        tvStreetAddress.text =street
        tvCountry.text = country
        tvPropertyPrice.text =price
        //lad image
        propertyImageView.load(data = Constants.BASE_URL_IMAGE+coverPhoto)
    }


}

