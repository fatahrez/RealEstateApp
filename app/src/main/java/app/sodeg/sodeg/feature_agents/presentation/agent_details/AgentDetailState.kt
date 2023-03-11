package app.sodeg.sodeg.feature_agents.presentation.agent_details

import app.sodeg.sodeg.feature_agents.domain.model.Agent


data class AgentDetailState(
    val agent: Agent? = null,
    val isLoading: Boolean = false
)
