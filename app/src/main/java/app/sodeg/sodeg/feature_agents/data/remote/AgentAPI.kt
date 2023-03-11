package app.sodeg.sodeg.feature_agents.data.remote

import app.sodeg.sodeg.feature_agents.data.remote.dto.AgentDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface AgentAPI {
    @GET("profile/agents/all/")
    suspend fun getAgentsDTO(): List<AgentDTO>

    @GET("profile/agents/detail/{id}")
    suspend fun getAgentDetails(@Path("id") id: Int): AgentDTO
}