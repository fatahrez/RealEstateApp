package com.example.guryihii.feature_agents.presentation.agent_list

import com.example.guryihii.feature_agents.domain.model.Agent

data class AgentListState(
    val agents: List<Agent> = emptyList(),
    val isLoading: Boolean = false
)
