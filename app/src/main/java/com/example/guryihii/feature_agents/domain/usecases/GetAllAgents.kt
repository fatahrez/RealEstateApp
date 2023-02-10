package com.example.guryihii.feature_agents.domain.usecases

import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.feature_agents.domain.model.Agent
import com.example.guryihii.feature_agents.domain.repository.AgentRepository
import kotlinx.coroutines.flow.Flow

class GetAllAgents(
    private val repository: AgentRepository
) {
    suspend operator fun invoke(): Flow<ResultWrapper<List<Agent>>> {
        return repository.getAllAgents()
    }
}