package com.example.besokmasak.core.di

import android.os.Looper
import com.example.besokmasak.core.data.source.RecipeRepository
import com.example.besokmasak.core.data.source.local.LocalDataSource
import com.example.besokmasak.core.data.source.remote.RemoteDataSource
import com.example.besokmasak.core.domain.repository.IRecipeRepository
import com.example.besokmasak.core.domain.usecase.RecipeInteractor
import com.example.besokmasak.core.domain.usecase.RecipeUseCase
import com.example.besokmasak.utils.AppExecutors
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepositoryInterface(recipeRepository: RecipeRepository): IRecipeRepository

    @Binds
    abstract fun provideUseCaseInterface(recipeInteractor: RecipeInteractor): RecipeUseCase


    //gausah juga karena sudah di cariin sama hilt
//    @Binds
//    abstract fun provideUseCaseInterface()

//    Ga usah karena sudah automatis di locate sama Hilt
/*    @Provides
    fun provideRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource,
        appExecutors: AppExecutors
    ): IRecipeRepository {
        return RecipeRepository(remoteDataSource,localDataSource,appExecutors)
    }*/

    //gausah karena hilt udah cari Coroutine Scope secara otomatis


}