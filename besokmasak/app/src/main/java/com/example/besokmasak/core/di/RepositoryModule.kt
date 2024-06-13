package com.example.besokmasak.core.di

import com.example.besokmasak.core.data.source.RecipeRepository
import com.example.besokmasak.core.data.source.local.LocalDataSource
import com.example.besokmasak.core.data.source.remote.RemoteDataSource
import com.example.besokmasak.core.domain.repository.IRecipeRepository
import com.example.besokmasak.utils.AppExecutors
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepositoryInterface(recipeRepository: RecipeRepository): IRecipeRepository

    @Provides
    fun provideRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource,
        appExecutors: AppExecutors
    ): IRecipeRepository {
        return RecipeRepository(remoteDataSource,localDataSource,appExecutors)
    }

}