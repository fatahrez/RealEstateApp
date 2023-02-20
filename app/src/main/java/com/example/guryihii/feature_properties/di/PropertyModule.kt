package com.example.guryihii.feature_properties.di

import android.content.SharedPreferences
import com.example.guryihii.core.shared.remote.AuthInterceptor
import com.example.guryihii.core.util.Constants
import com.example.guryihii.core.shared.remote.HttpClient
import com.example.guryihii.core.shared.remote.HttpLogger
import com.example.guryihii.core.shared.remote.TokenAuthenticator
import com.example.guryihii.feature_properties.data.remote.PropertyAPI
import com.example.guryihii.feature_properties.data.repository.PropertyRepositoryImpl
import com.example.guryihii.feature_properties.domain.repository.PropertyRepository
import com.example.guryihii.feature_properties.domain.usecases.GetAllProperties
import com.example.guryihii.feature_properties.domain.usecases.GetPropertyDetails
import com.example.guryihii.feature_properties.domain.usecases.GetSellerProperties
import com.example.guryihii.feature_properties.domain.usecases.PostProperty
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
    @Singleton
    fun providesGetPropertyDetailUseCase(
        repository: PropertyRepository
    ): GetPropertyDetails {
        return GetPropertyDetails(repository)
    }

    @Provides
    @Singleton
    fun providesPostPropertyUseCase(
        repository: PropertyRepository
    ): PostProperty {
        return PostProperty(repository)
    }

    @Provides
    @Singleton
    fun providesGetSellerPropertiesUseCase(
        repository: PropertyRepository
    ): GetSellerProperties {
        return GetSellerProperties(repository)
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
    fun providesAuthInterceptor(
        sharedPreferences: SharedPreferences
    ): AuthInterceptor = AuthInterceptor(sharedPreferences)
    @Provides
    fun providesOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor,
        authenticator: TokenAuthenticator
    ): OkHttpClient {
        return HttpClient.setupOkHttpClient(
            httpLoggingInterceptor,
            authInterceptor,
            authenticator
        )
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