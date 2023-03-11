package app.sodeg.sodeg.feature_properties.presentation.property_detail


import coil.load
import com.airbnb.epoxy.TypedEpoxyController
import com.example.garissaestore.epoxy.ViewBindingKotlinModel
import app.sodeg.sodeg.R
import app.sodeg.sodeg.core.util.Constants
import app.sodeg.sodeg.databinding.ListPropertyDetailBinding

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

