package com.example.guryihii.feature_properties.presentation.all_property_listings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.guryihii.core.util.Constants
import com.example.guryihii.databinding.ListItemPropertyListingBinding
import com.example.guryihii.feature_properties.domain.model.PropertyListing

class AllPropertyListingAdapter(
    private val clickListener: (PropertyListing) -> Unit
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
                propertyImageView.load(Constants.BASE_URL_IMAGE+propertyListing.property.coverPhoto)
            }
        }

    }

}