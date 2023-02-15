package com.example.guryihii.feature_refresh_token.di

import com.example.guryihii.core.util.Constants
import com.example.guryihii.feature_refresh_token.data.remote.RefreshTokenApi
import com.example.guryihii.feature_refresh_token.data.repository.RefreshTokenRepositoryImpl
import com.example.guryihii.feature_refresh_token.domain.repository.RefreshTokenRepository
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
object RefreshTokenModule {

    @Provides
    @Singleton
    fun providesRefreshTokenRepository(
        refreshTokenApi: RefreshTokenApi
    ): RefreshTokenRepository {
        return RefreshTokenRepositoryImpl(refreshTokenApi)
    }


    @Provides
    @Singleton
    fun providesRefreshTokenApi(okHttpClient: OkHttpClient): RefreshTokenApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(RefreshTokenApi::class.java)
    }
}