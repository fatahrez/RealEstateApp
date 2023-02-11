package com.example.guryihii.feature_agents.domain.usecases

import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.feature_agents.domain.model.Agent
import com.example.guryihii.feature_agents.domain.repository.AgentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetAgentDetails(
    private val repository: AgentRepository
) {
    suspend operator fun invoke(id: Int): Flow<ResultWrapper<Agent>> {
        return repository.getAgentDetails(id)
    }
}