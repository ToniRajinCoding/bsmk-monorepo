package com.example.besokmasak.core.di

import android.content.Context
import com.example.besokmasak.core.data.source.remote.RemoteDataSource
import com.example.besokmasak.core.data.source.remote.network.ApiConfig
import com.example.besokmasak.core.domain.repository.IRecipeRepository
import com.example.besokmasak.core.domain.usecase.RecipeInteractor
import com.example.besokmasak.core.domain.usecase.RecipeUseCase
import com.example.besokmasak.utils.AppExecutors

object Injection {

    private fun provideRepository(context: Context) : IRecipeRepository {

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val appExecutor = AppExecutors()

        return

    }

    fun provideRecipeUseCase(context: Context): RecipeUseCase {
        val repository = provideRecipeUseCase(context)
        return RecipeInteractor(repository)
    }
}