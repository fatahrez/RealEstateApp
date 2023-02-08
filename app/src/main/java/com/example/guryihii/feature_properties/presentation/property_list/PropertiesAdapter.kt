package com.example.guryihii.feature_properties.presentation.property_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.guryihii.core.util.Constants
import com.example.guryihii.databinding.ListItemPropertyBinding
import com.example.guryihii.feature_properties.domain.model.Property

class PropertiesAdapter(
    private val clickListener: (Property) -> Unit
):
    ListAdapter<Property, PropertiesAdapter.ViewHolder>(COMPARATOR) {

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
            ListItemPropertyBinding.inflate(
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
        private val binding: ListItemPropertyBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bindItem(property: Property) {
            with(binding) {
                propertyTitle.text = property.title
                propertyImageView.load(Constants.BASE_URL_IMAGE+property.coverPhoto)
            }
        }

    }
}