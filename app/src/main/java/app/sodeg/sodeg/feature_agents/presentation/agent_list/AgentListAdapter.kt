package app.sodeg.sodeg.feature_agents.presentation.agent_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.sodeg.sodeg.databinding.ListItemAgentBinding
import app.sodeg.sodeg.feature_agents.domain.model.Agent
import coil.load

class AgentListAdapter(
    private val clickListener: (Agent) -> Unit
): ListAdapter<Agent, AgentListAdapter.ViewHolder>(COMPARATOR) {

    private object COMPARATOR: DiffUtil.ItemCallback<Agent>() {
        override fun areItemsTheSame(oldItem: Agent, newItem: Agent): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Agent, newItem: Agent): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemAgentBinding.inflate(
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
        private val binding: ListItemAgentBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bindItem(agent: Agent) {
            with(binding) {
                agentName.text = agent.firstName
                profilePhoto.load(agent.profilePhoto)
                agentEmail.text ="Email: " + agent.email
                agentPhoneNumber.text = "Phone: " + agent.phoneNumber
                agentAboutMe.text = "About Me: " + agent.aboutMe
            }
        }
    }
}