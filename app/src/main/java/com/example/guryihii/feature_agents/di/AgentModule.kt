package com.example.guryihii.feature_agents.di


import com.example.guryihii.core.shared.remote.HttpClient
import com.example.guryihii.core.shared.remote.HttpLogger
import com.example.guryihii.core.util.Constants
import com.example.guryihii.feature_agents.data.remote.AgentAPI
import com.example.guryihii.feature_agents.data.repository.AgentRepositoryImpl
import com.example.guryihii.feature_agents.domain.repository.AgentRepository
import com.example.guryihii.feature_agents.domain.usecases.GetAgentDetails
import com.example.guryihii.feature_agents.domain.usecases.GetAllAgents
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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