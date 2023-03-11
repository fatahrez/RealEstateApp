package com.example.guryihii.feature_properties.presentation.all_property_listings

import android.content.Context
import android.content.res.Resources.Theme
import android.text.Spannable
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.guryihii.R
import com.example.guryihii.core.util.Constants
import com.example.guryihii.databinding.ListItemPropertyListingBinding
import com.example.guryihii.feature_properties.domain.model.PropertyListing

class AllPropertyListingAdapter(
    private val clickListener: (PropertyListing) -> Unit,
    private val context: Context
): ListAdapter<PropertyListing, AllPropertyListingAdapter.ViewHolder>(COMPARATOR) {

    private object COMPARATOR: DiffUtil.ItemCallback<PropertyListing>() {
        override fun areItemsTheSame(oldItem: PropertyListing, newItem: PropertyListing): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: PropertyListing,
            newItem: PropertyListing
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemPropertyListingBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bindItem(item)
        holder.itemView.setOnClickListener{
            clickListener(item)
        }
    }

    inner class ViewHolder(
        private val binding: ListItemPropertyListingBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bindItem(propertyListing: PropertyListing) {
            with(binding) {
                val property = propertyListing.property
                propertyImageView.load(Constants.BASE_URL_IMAGE+ property.coverPhoto)
                val priceString = "$ " + property.price
                val spannable = SpannableString(priceString)
                spannable.setSpan(
                    BackgroundColorSpan(context.resources.getColor(R.color.green_dark)),
                    0,
                    priceString.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                priceTextView.text = spannable
                val propertyDetails = property.propertyType + "  |  " + property.city + "  |  " +
                        property.bedrooms + " Bedroom" + "  |  " + property.streetAddress
                propertyDetailTextView.text = propertyDetails
            }
        }

    }

}