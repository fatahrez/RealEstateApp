package com.example.guryihii.feature_agents.presentation.agent_details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.guryihii.R
import com.example.guryihii.databinding.FragmentAgentDetailBinding
import com.example.guryihii.feature_agents.domain.model.Agent
import kotlinx.coroutines.flow.collect


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
                    Log.i("TAG", "observeViewState: loading...")
                } else {
                    Log.i("TAG", "observeViewState: ${state.agent}")
                    showAgentDetails(state.agent)
                }
            }
        }
    }

    private fun showAgentDetails(agent: Agent?) {
        if (agent != null) {
            with(binding) {

            }
        }
    }

    companion object {

    }
}