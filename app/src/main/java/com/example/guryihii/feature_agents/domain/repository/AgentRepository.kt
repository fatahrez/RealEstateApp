package com.example.guryihii.feature_agents.domain.repository

import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.feature_agents.domain.model.Agent
import kotlinx.coroutines.flow.Flow

interface AgentRepository {
    suspend fun getAllAgents(): Flow<ResultWrapper<List<Agent>>>
}