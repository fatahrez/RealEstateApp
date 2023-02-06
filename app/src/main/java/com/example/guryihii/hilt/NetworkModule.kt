package com.example.guryihii.hilt

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.guryihii.MainActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.time.Duration
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient):Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http://24.199.124.221/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        val duration = Duration.ofSeconds(30)
        return OkHttpClient.Builder()
            .connectTimeout(duration)
            .readTimeout(duration)
            .writeTimeout(duration)
            .build()
    }

    @Provides
    @Singleton
    fun providesPropertiesService(retrofit: Retrofit): MainActivity.PropertiesService {
        return retrofit.create(MainActivity.PropertiesService::class.java)
    }
}