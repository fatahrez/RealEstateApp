package app.sodeg.sodeg.feature_agents.di

import app.sodeg.sodeg.core.util.Constants
import app.sodeg.sodeg.feature_agents.data.remote.AgentAPI
import app.sodeg.sodeg.feature_agents.data.repository.AgentRepositoryImpl
import app.sodeg.sodeg.feature_agents.domain.repository.AgentRepository
import app.sodeg.sodeg.feature_agents.domain.usecases.GetAgentDetails
import app.sodeg.sodeg.feature_agents.domain.usecases.GetAllAgents
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AgentModule {

    @Provides
    @Singleton
    fun providesGetAgentsUseCase(
        repository: AgentRepository
    ): GetAllAgents {
        return GetAllAgents(repository)
    }
    
    @Provides
    @Singleton
    fun providesGetAllAgents(
        repository: AgentRepository
    ): GetAgentDetails {
        return GetAgentDetails(repository)
    }

    @Provides
    fun providesAgentRepository(
        agentAPI: AgentAPI
    ): AgentRepository {
        return AgentRepositoryImpl(agentAPI)
    }

    @Provides
    @Singleton
    fun providesAgent(okHttpClient: OkHttpClient): AgentAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(AgentAPI::class.java)
    }

}