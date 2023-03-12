package app.sodeg.sodeg.feature_newProjects.di

import app.sodeg.sodeg.core.util.Constants
import app.sodeg.sodeg.feature_newProjects.data.remote.NewProjectAPI
import app.sodeg.sodeg.feature_newProjects.data.repository.NewProjectRepositoryImpl
import app.sodeg.sodeg.feature_newProjects.domain.repository.NewProjectRepository
import app.sodeg.sodeg.feature_newProjects.domain.usecases.*
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
    fun providesPostNewProjectUseCase(
        repository: NewProjectRepository
    ): PostNewProject {
        return PostNewProject(repository)
    }

    @Provides
    @Singleton
    fun providesUpdateNewProjectUseCase(
        repository: NewProjectRepository
    ): UpdateNewProject {
        return UpdateNewProject(repository)
    }

    @Provides
    @Singleton
    fun providesDeleteNewProjectUseCase(
        repository: NewProjectRepository
    ): DeleteNewProject {
        return DeleteNewProject(repository)
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