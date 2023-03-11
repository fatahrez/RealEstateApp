package app.sodeg.sodeg.feature_properties.di

import android.content.SharedPreferences
import app.sodeg.sodeg.core.shared.remote.AuthInterceptor
import app.sodeg.sodeg.core.util.Constants
import app.sodeg.sodeg.core.shared.remote.HttpClient
import app.sodeg.sodeg.core.shared.remote.HttpLogger
import app.sodeg.sodeg.core.shared.remote.TokenAuthenticator
import app.sodeg.sodeg.feature_properties.data.remote.PropertyAPI
import app.sodeg.sodeg.feature_properties.data.repository.PropertyRepositoryImpl
import app.sodeg.sodeg.feature_properties.domain.repository.PropertyRepository
import app.sodeg.sodeg.feature_properties.domain.usecases.*
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
    fun providesDeletePropertyUseCase(
        repository: PropertyRepository
    ): DeleteProperty {
        return DeleteProperty(repository)
    }

    @Provides
    @Singleton
    fun providesUpdatePropertyUseCase(
        repository: PropertyRepository
    ): UpdateProperty {
        return UpdateProperty(repository)
    }

    @Provides
    @Singleton
    fun providesPostPropertyListingUseCase(
        repository: PropertyRepository
    ): PostPropertyListing {
        return PostPropertyListing(repository)
    }

    @Provides
    @Singleton
    fun providesGetAgentPropertyListingUseCase(
        repository: PropertyRepository
    ): GetAgentPropertyListing {
        return GetAgentPropertyListing(repository)
    }

    @Provides
    @Singleton
    fun providesGetSellerPropertyListingUseCase(
        repository: PropertyRepository
    ): GetSellerPropertyListing {
        return GetSellerPropertyListing(repository)
    }

    @Provides
    @Singleton
    fun providesGetAllPropertyListingUseCase(
        repository: PropertyRepository
    ): GetAllPropertyListing {
        return GetAllPropertyListing(repository)
    }

    @Provides
    @Singleton
    fun providesGetPropertyListingDetailsUseCase(
        repository: PropertyRepository
    ): GetPropertyListingDetails {
        return GetPropertyListingDetails(repository)
    }

    @Provides
    @Singleton
    fun providesDeletePropertyListingUseCase(
        repository: PropertyRepository
    ): DeletePropertyListing {
        return DeletePropertyListing(repository)
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