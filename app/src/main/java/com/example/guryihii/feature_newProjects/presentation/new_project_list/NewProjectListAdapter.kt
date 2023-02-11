package com.example.guryihii.feature_newProjects.presentation.new_project_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.guryihii.databinding.ListItemNewProjectBinding
import com.example.guryihii.feature_newProjects.domain.model.NewProject

class NewProjectListAdapter(
    private val clickListener: (NewProject) -> Unit
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

            }
        }

    }
}