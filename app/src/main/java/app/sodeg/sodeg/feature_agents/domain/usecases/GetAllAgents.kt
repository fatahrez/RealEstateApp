package app.sodeg.sodeg.feature_agents.domain.usecases


import app.sodeg.sodeg.core.util.ResultWrapper
import app.sodeg.sodeg.feature_agents.domain.model.Agent
import app.sodeg.sodeg.feature_agents.domain.repository.AgentRepository
import kotlinx.coroutines.flow.Flow

class GetAllAgents(
    private val repository: AgentRepository
) {
    suspend operator fun invoke(): Flow<ResultWrapper<List<Agent>>> {
        return repository.getAllAgents()
    }
}