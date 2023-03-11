package app.sodeg.sodeg.feature_agents.domain.repository


import app.sodeg.sodeg.core.util.ResultWrapper
import app.sodeg.sodeg.feature_agents.domain.model.Agent
import kotlinx.coroutines.flow.Flow

interface AgentRepository {
    suspend fun getAllAgents(): Flow<ResultWrapper<List<Agent>>>

    suspend fun getAgentDetails(id: Int): Flow<ResultWrapper<Agent>>
}