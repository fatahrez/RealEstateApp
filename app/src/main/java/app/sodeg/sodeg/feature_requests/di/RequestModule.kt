package app.sodeg.sodeg.feature_requests.di

import app.sodeg.sodeg.core.util.Constants
import app.sodeg.sodeg.feature_requests.data.remote.RequestAPI
import app.sodeg.sodeg.feature_requests.data.repository.RequestRepositoryImpl
import app.sodeg.sodeg.feature_requests.domain.repository.RequestRepository
import app.sodeg.sodeg.feature_requests.domain.usecases.GetAllRequests
import app.sodeg.sodeg.feature_requests.domain.usecases.GetRequestPropertyDetails
import app.sodeg.sodeg.feature_requests.domain.usecases.PostRequest
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