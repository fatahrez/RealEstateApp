package com.example.guryihii.feature_agents.presentation.agent_details

import com.example.guryihii.feature_agents.domain.model.Agent

data class AgentDetailState(
    val agent: Agent? = null,
    val isLoading: Boolean = false
)
