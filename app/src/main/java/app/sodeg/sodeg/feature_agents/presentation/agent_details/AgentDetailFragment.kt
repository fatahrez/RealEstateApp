package app.sodeg.sodeg.feature_agents.presentation.agent_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.sodeg.sodeg.core.util.gone
import app.sodeg.sodeg.core.util.visible
import app.sodeg.sodeg.databinding.FragmentAgentDetailBinding
import app.sodeg.sodeg.feature_agents.domain.model.Agent
import coil.load
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AgentDetailFragment : Fragment() {

    private var _binding: FragmentAgentDetailBinding? = null
    private val binding: FragmentAgentDetailBinding get() = _binding!!

    private val viewModel: AgentDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAgentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        fetchData()
        observeViewState()
    }

    private fun fetchData() {
        val id = arguments?.getInt("agent_id")
        if (id != null) {
            viewModel.showAgentDetails(id)
        }
    }

    private fun observeViewState() {
        lifecycleScope.launchWhenCreated {
            viewModel.state.collect { state ->
                if (state.isLoading) {
                    binding.progressBar.visible()
                } else {
                    binding.progressBar.gone()
                    showAgentDetails(state.agent)
                }
            }
        }
    }

    private fun showAgentDetails(agent: Agent?) {
        if (agent != null) {
            with(binding) {
                agentProfilePhoto.load(agent.profilePhoto)
                agentName.text = agent.firstName
                agentEmail.text = "Email: " + agent.email
                agentPhoneNumber.text = "Phone: " + agent.phoneNumber
                agentAboutMe.text = "About Me: " + agent.aboutMe
                agentLicense.text = "License: " + agent.license
                agentGender.text = "Gender: " + agent.gender
                agentLocation.text = "Location: " + agent.city + ", " + agent.country
            }
        }
    }

    companion object {

    }
}