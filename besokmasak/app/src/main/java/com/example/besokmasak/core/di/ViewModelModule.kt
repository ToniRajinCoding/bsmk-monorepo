package com.example.besokmasak.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ViewModelModule{

    @Provides
    @Singleton
    fun provideCoroutineScope(): CoroutineScope {
        val supervisorJob = SupervisorJob()
        val ioDispatcher = Dispatchers.IO
        return CoroutineScope(supervisorJob + ioDispatcher)
    }


}