package app.sodeg.sodeg.feature_properties.presentation.property_list

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import app.sodeg.sodeg.R
import app.sodeg.sodeg.core.util.Constants
import app.sodeg.sodeg.databinding.ListItemPropertyBinding
import app.sodeg.sodeg.feature_properties.domain.model.Property

class PropertiesAdapter(
    private val clickListener: (Property) -> Unit,
    private val context: Context
): ListAdapter<Property, PropertiesAdapter.ViewHolder>(COMPARATOR) {

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
                propertyImageView.load(Constants.BASE_URL_IMAGE+ property.coverPhoto)
                val priceString = "$ " + property.price
                val spannable = SpannableString(priceString)
                spannable.setSpan(
                    BackgroundColorSpan(context
                        .resources.getColor(R.color.green_dark, context.theme)),
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