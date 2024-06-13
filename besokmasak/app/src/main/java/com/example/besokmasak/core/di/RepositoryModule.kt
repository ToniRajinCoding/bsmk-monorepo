package com.example.besokmasak.core.di

import com.example.besokmasak.core.data.source.RecipeRepository
import com.example.besokmasak.core.domain.repository.IRecipeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class,DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepositor(recipeRepository: RecipeRepository) :IRecipeRepository

}