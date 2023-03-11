package app.sodeg.sodeg.feature_requests.presentation.request_list


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.sodeg.sodeg.databinding.ListItemPropertyRequestBinding
import app.sodeg.sodeg.feature_requests.domain.model.RequestProperty

class PropertyRequestAdapter(
    private val clickListener: (RequestProperty) -> Unit
): ListAdapter<RequestProperty, PropertyRequestAdapter.ViewHolder>(COMPARATOR) {

    private object COMPARATOR: DiffUtil.ItemCallback<RequestProperty>() {
        override fun areItemsTheSame(oldItem: RequestProperty, newItem: RequestProperty): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: RequestProperty,
            newItem: RequestProperty
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemPropertyRequestBinding.inflate(
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
        private val binding: ListItemPropertyRequestBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bindItem(requestProperty: RequestProperty) {
            binding.nameTextView.text = requestProperty.name
            binding.phoneNumberTextView.text = requestProperty.phoneNumber
            binding.emailTextView.text = requestProperty.email
            binding.subjectTextView.text = requestProperty.subject
        }
    }


}