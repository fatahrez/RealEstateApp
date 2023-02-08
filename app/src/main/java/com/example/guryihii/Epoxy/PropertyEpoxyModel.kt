package com.example.guryihii.Epoxy

import coil.load
import com.example.guryihii.R
import com.example.guryihii.ViewBindingKotlinModel
import com.example.guryihii.databinding.EpoxyModelPropertyItemBinding
import com.example.guryihii.model.domain.Property

class PropertyEpoxyModel(
    val property: Property
): ViewBindingKotlinModel<EpoxyModelPropertyItemBinding>(R.layout.epoxy_model_property_item) {
    override fun EpoxyModelPropertyItemBinding.bind() {
        //Load image
        propertyImageView.load(data = property.profile_photo)

        tvTitle.text = property.title
        tvCity.text =property.city
        tvPrice.text = property.price.toString()
        tvSlug.text = property.slug
    }
}
