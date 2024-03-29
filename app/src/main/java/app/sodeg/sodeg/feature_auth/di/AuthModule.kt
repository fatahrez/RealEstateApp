package app.sodeg.sodeg.feature_auth.di

import android.content.Context
import android.content.SharedPreferences
import app.sodeg.sodeg.core.util.Constants
import app.sodeg.sodeg.feature_auth.data.remote.AuthAPI
import app.sodeg.sodeg.feature_auth.data.repository.AuthRepositoryImpl
import app.sodeg.sodeg.feature_auth.domain.repository.AuthRepository
import app.sodeg.sodeg.feature_auth.domain.usecases.PostSignInUser
import app.sodeg.sodeg.feature_auth.domain.usecases.PostSignUpUser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    fun providesPostSignUpUserUseCase(
        authRepository: AuthRepository
    ): PostSignUpUser {
        return PostSignUpUser(authRepository)
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

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext appContext: Context): SharedPreferences {
        return appContext.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
    }
}