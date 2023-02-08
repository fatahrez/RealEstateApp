package com.example.guryihii.feature_properties.di

import com.example.guryihii.core.util.Constants
import com.example.guryihii.feature_properties.data.remote.HttpClient
import com.example.guryihii.feature_properties.data.remote.HttpLogger
import com.example.guryihii.feature_properties.data.remote.PropertyAPI
import com.example.guryihii.feature_properties.data.repository.PropertyRepositoryImpl
import com.example.guryihii.feature_properties.domain.repository.PropertyRepository
import com.example.guryihii.feature_properties.domain.usecases.GetAllProperties
import com.example.guryihii.feature_properties.domain.usecases.GetPropertyDetails
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PropertyModule {

    @Provides
    @Singleton
    fun providesGetAllPropertiesUseCase(
        repository: PropertyRepository
    ): GetAllProperties {
        return GetAllProperties(repository)
    }

    @Provides
    fun providesGetPropertyDetailUseCase(
        repository: PropertyRepository
    ): GetPropertyDetails {
        return GetPropertyDetails(repository)
    }

    @Provides
    @Singleton
    fun providesPropertyRepository(
        propertyAPI: PropertyAPI
    ): PropertyRepository {
        return PropertyRepositoryImpl(propertyAPI)
    }

    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor = HttpLogger.create()

    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return HttpClient.setupOkHttpClient(httpLoggingInterceptor)
    }

    @Provides
    @Singleton
    fun providePropertyApi(okHttpClient: OkHttpClient): PropertyAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(PropertyAPI::class.java)
    }

    @Provides
    fun providesIODispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }
}