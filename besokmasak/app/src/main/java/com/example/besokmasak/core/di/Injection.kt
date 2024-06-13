package com.example.besokmasak.core.di

import android.content.Context
import com.example.besokmasak.core.data.source.RecipeRepository
import com.example.besokmasak.core.data.source.local.LocalDataSource
import com.example.besokmasak.core.data.source.local.room.RecipeDatabase
import com.example.besokmasak.core.data.source.remote.RemoteDataSource
import com.example.besokmasak.core.data.source.remote.network.ApiConfig
import com.example.besokmasak.core.domain.repository.IRecipeRepository
import com.example.besokmasak.core.domain.usecase.RecipeInteractor
import com.example.besokmasak.core.domain.usecase.RecipeUseCase
import com.example.besokmasak.utils.AppExecutors


object Injection {

//    private fun provideRepository(context: Context) : IRecipeRepository {
//
//        val database = RecipeDatabase.getInstance(context)
//
//        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
//        val localDataSource = LocalDataSource.getInstance(database.recipeDao())
//        val appExecutor = AppExecutors()
//
//        return RecipeRepository.getInstance(remoteDataSource,localDataSource,appExecutor)
//
//    }
//
//    fun provideRecipeUseCase(context: Context): RecipeUseCase {
//        val repository = provideRepository(context)
//        return RecipeInteractor(repository)
//    }
}