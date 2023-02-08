package com.example.guryihii.Epoxy

import androidx.core.view.isGone
import androidx.core.view.isVisible
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
        productImageViewLoadingProgressBar.isVisible = true
        propertyImageView.load("http://24.199.124.221"+property.cover_photo){
            listener { request, result ->
                productImageViewLoadingProgressBar.isGone = true
            }
        }

        tvTitle.text = property.title
    }
}
