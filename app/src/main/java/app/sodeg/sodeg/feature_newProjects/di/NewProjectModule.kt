package app.sodeg.sodeg.feature_newProjects.di

import app.sodeg.sodeg.core.util.Constants
import app.sodeg.sodeg.feature_newProjects.data.remote.NewProjectAPI
import app.sodeg.sodeg.feature_newProjects.data.repository.NewProjectRepositoryImpl
import app.sodeg.sodeg.feature_newProjects.domain.repository.NewProjectRepository
import app.sodeg.sodeg.feature_newProjects.domain.usecases.GetAllNewProjects
import app.sodeg.sodeg.feature_newProjects.domain.usecases.GetNewProjectDetails
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
object NewProjectModule {

    @Provides
    @Singleton
    fun providesGetAllNewProjectsUseCase(
        repository: NewProjectRepository
    ): GetAllNewProjects {
        return GetAllNewProjects(repository)
    }

    @Provides
    @Singleton
    fun providesGetNewProjectUseCase(
        repository: NewProjectRepository
    ): GetNewProjectDetails {
        return GetNewProjectDetails(repository)
    }

    @Provides
    @Singleton
    fun providesNewProjectsRepository(
        newProjectAPI: NewProjectAPI
    ): NewProjectRepository {
        return NewProjectRepositoryImpl(newProjectAPI)
    }

    @Provides
    @Singleton
    fun providesNewProjectApi(okHttpClient: OkHttpClient): NewProjectAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(NewProjectAPI::class.java)
    }

}