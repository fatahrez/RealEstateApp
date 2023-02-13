package com.example.guryihii.feature_profile.di

import com.example.guryihii.core.util.Constants
import com.example.guryihii.feature_profile.data.remote.ProfileAPI
import com.example.guryihii.feature_profile.data.repository.ProfileRepositoryImpl
import com.example.guryihii.feature_profile.domain.repository.ProfileRepository
import com.example.guryihii.feature_profile.domain.usecases.GetProfile
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
object ProfileModule {

    @Provides
    @Singleton
    fun providesGetProfileUseCase(
        profileRepository: ProfileRepository
    ): GetProfile {
        return GetProfile(profileRepository)
    }

    @Provides
    @Singleton
    fun providesProfileRepository(
        profileAPI: ProfileAPI
    ): ProfileRepository{
        return ProfileRepositoryImpl(profileAPI)
    }

    @Provides
    @Singleton
    fun providesProfileAPI(okHttpClient: OkHttpClient): ProfileAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ProfileAPI::class.java)
    }
}