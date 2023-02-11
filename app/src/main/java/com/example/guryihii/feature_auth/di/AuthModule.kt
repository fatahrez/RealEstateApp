package com.example.guryihii.feature_auth.di

import com.example.guryihii.core.util.Constants
import com.example.guryihii.feature_auth.data.remote.AuthAPI
import com.example.guryihii.feature_auth.data.repository.AuthRepositoryImpl
import com.example.guryihii.feature_auth.domain.repository.AuthRepository
import com.example.guryihii.feature_auth.domain.usecases.PostSignInUser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun providesPostSignInUserUseCase(
        authRepository: AuthRepository
    ): PostSignInUser {
        return PostSignInUser(authRepository)
    }

    @Provides
    @Singleton
    fun providesAuthRepository(
        authAPI: AuthAPI
    ): AuthRepository {
        return AuthRepositoryImpl(authAPI)
    }

    @Provides
    @Singleton
    fun providesAuthApi(okHttpClient: OkHttpClient): AuthAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(AuthAPI::class.java)
    }
}