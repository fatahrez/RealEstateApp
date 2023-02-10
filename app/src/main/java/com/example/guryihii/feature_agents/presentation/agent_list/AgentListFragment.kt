package com.example.guryihii.feature_agents.presentation.agent_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.guryihii.R
import com.example.guryihii.core.util.gone
import com.example.guryihii.core.util.visible
import com.example.guryihii.databinding.FragmentAgentListBinding
import com.example.guryihii.feature_agents.domain.model.Agent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AgentListFragment : Fragment() {
    private var _binding: FragmentAgentListBinding? = null
    private val binding: FragmentAgentListBinding get() = _binding!!

    private val viewModel: AgentListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAgentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        val adapter = createAdapter()
        setupRecyclerView(adapter)
        observeViewState(adapter)
    }

    private fun observeViewState(adapter: AgentListAdapter) {
        lifecycleScope.launchWhenCreated {
            viewModel.state.collect { state ->
                if (state.isLoading) {
                    binding.progressBar.visible()
                } else {
                    binding.progressBar.gone()
                    binding.noData.run {
                        if (state.agents.isEmpty()) visible() else gone()
                    }
                    adapter.submitList(state.agents)
                }
            }
        }
    }

    private fun setupRecyclerView(agentsAdapter: AgentListAdapter) {
        binding.recyclerView.apply {
            adapter = agentsAdapter
            setHasFixedSize(true)
        }
    }

    private fun createAdapter(): AgentListAdapter {
        return AgentListAdapter {
            navToAgentDetail(it)
        }
    }

    private fun navToAgentDetail(agent: Agent) {

    }

    companion object {

    }
}