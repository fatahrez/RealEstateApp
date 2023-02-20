package com.example.guryihii.feature_properties.presentation.seller_properties

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.guryihii.core.util.Constants
import com.example.guryihii.databinding.ListItemSellerPropertyBinding
import com.example.guryihii.feature_properties.domain.model.Property

class SellerPropertiesAdapter(
    private val clickListener: (Property) -> Unit
):  ListAdapter<Property, SellerPropertiesAdapter.ViewHolder>(COMPARATOR){

    private object COMPARATOR: DiffUtil.ItemCallback<Property>() {
        override fun areItemsTheSame(oldItem: Property, newItem: Property): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Property, newItem: Property): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemSellerPropertyBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bindItem(item)
        holder.itemView.setOnClickListener {
            clickListener(item)
        }
    }

    inner class ViewHolder(
        private val binding: ListItemSellerPropertyBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bindItem(property: Property) {
            with(binding) {
                coverPhotoImageView.load(Constants.BASE_URL_IMAGE+property.coverPhoto)
            }
        }
    }
}