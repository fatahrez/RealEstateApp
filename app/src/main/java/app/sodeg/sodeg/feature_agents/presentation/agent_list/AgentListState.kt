package app.sodeg.sodeg.feature_agents.presentation.agent_list

import app.sodeg.sodeg.feature_agents.domain.model.Agent

data class AgentListState(
    val agents: List<Agent> = emptyList(),
    val isLoading: Boolean = false
)
