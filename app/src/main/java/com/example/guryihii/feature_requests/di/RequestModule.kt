package com.example.guryihii.feature_requests.di

import com.example.guryihii.feature_requests.domain.repository.RequestRepository
import com.example.guryihii.feature_requests.domain.usecases.GetAllRequests
import com.example.guryihii.feature_requests.domain.usecases.PostRequest
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RequestModule {

    @Provides
    @Singleton
    fun providesGetAllRequestsUseCase(
        repository: RequestRepository
    ): GetAllRequests {
        return GetAllRequests(repository)
    }

    @Provides
    @Singleton
    fun providesPostRequestUseCase(
        repository: RequestRepository
    ): PostRequest {
        return PostRequest(repository)
    }

    @Provides
    @Singleton
    fun providesRequestRepository(

    ) {

    }

}