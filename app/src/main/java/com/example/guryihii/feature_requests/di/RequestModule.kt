package com.example.guryihii.feature_requests.di

import com.example.guryihii.core.util.Constants
import com.example.guryihii.feature_requests.data.remote.RequestAPI
import com.example.guryihii.feature_requests.data.repository.RequestRepositoryImpl
import com.example.guryihii.feature_requests.domain.repository.RequestRepository
import com.example.guryihii.feature_requests.domain.usecases.GetAllRequests
import com.example.guryihii.feature_requests.domain.usecases.GetRequestPropertyDetails
import com.example.guryihii.feature_requests.domain.usecases.PostRequest
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
    fun providesGetRequestPropertyDetailsUseCase(
        repository: RequestRepository
    ): GetRequestPropertyDetails {
        return GetRequestPropertyDetails(repository)
    }

    @Provides
    @Singleton
    fun providesRequestRepository(
        api: RequestAPI
    ): RequestRepository {
        return RequestRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun providesRequestAPI(okHttpClient: OkHttpClient): RequestAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(RequestAPI::class.java)
    }

}