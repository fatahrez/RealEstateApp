package app.sodeg.sodeg.feature_agents.data.repository

import app.sodeg.sodeg.core.util.ResultWrapper
import app.sodeg.sodeg.core.util.safeApiCall
import app.sodeg.sodeg.feature_agents.data.remote.AgentAPI
import app.sodeg.sodeg.feature_agents.domain.model.Agent
import app.sodeg.sodeg.feature_agents.domain.repository.AgentRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class AgentRepositoryImpl(
    private val apiService: AgentAPI,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): AgentRepository {
    override suspend fun getAllAgents(): Flow<ResultWrapper<List<Agent>>> =
        safeApiCall(ioDispatcher) {
        apiService.getAgentsDTO().map {
            it.toAgent()
        }
    }

    override suspend fun getAgentDetails(id: Int): Flow<ResultWrapper<Agent>> =
        safeApiCall(ioDispatcher) {
        apiService.getAgentDetails(id).toAgent()
    }
}