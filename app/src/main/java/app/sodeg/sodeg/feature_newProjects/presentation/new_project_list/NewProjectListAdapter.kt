package app.sodeg.sodeg.feature_newProjects.presentation.new_project_list

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.sodeg.sodeg.R
import app.sodeg.sodeg.core.util.Constants
import app.sodeg.sodeg.databinding.ListItemNewProjectBinding
import app.sodeg.sodeg.feature_newProjects.domain.model.NewProject
import coil.load

class NewProjectListAdapter(
    private val clickListener: (NewProject) -> Unit,
    private val context: Context
): ListAdapter<NewProject, NewProjectListAdapter.ViewHolder>(COMPARATOR) {

    private object COMPARATOR: DiffUtil.ItemCallback<NewProject>() {
        override fun areItemsTheSame(oldItem: NewProject, newItem: NewProject): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NewProject, newItem: NewProject): Boolean {
            return oldItem == newItem
        }

    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bindItem(item)
        holder.itemView.setOnClickListener {
            clickListener(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemNewProjectBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }
    inner class ViewHolder(
        private val binding: ListItemNewProjectBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bindItem(newProject: NewProject) {
            with(binding) {
                propertyImageView.load(Constants.BASE_URL_IMAGE+ newProject.coverPhoto)
                val priceString = "$ " + newProject.price
                val spannable = SpannableString(priceString)
                spannable.setSpan(
                    BackgroundColorSpan(context.resources.getColor(R.color.green_dark, context.theme)),
                    0,
                    priceString.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                priceTextView.text = spannable
                val propertyDetails = newProject.propertyType + "  |  " + newProject.city + "  |  " +
                        newProject.bedrooms + " Bedroom"
                propertyDetailTextView.text = propertyDetails
            }
        }
    }
}