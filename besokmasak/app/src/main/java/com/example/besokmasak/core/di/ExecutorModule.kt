package com.example.besokmasak.core.di

import android.os.Looper
import com.example.besokmasak.utils.AppExecutors
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

@Module
@InstallIn(SingletonComponent::class)
class ExecutorModule {

    @Provides
    @Singleton
    fun provideAppExecutors() : AppExecutors {
        return AppExecutors(
            Executors.newSingleThreadExecutor(),
            Executors.newFixedThreadPool(AppExecutors.THREAD_COUNT),
            MainThreadExecutor()
        )
    }

    private class MainThreadExecutor : Executor {
        private val mainThreadHandler = android.os.Handler(Looper.getMainLooper())
        override fun execute(command: Runnable?) {
            TODO("Not yet implemented")
        }
    }

    @Provides
    @Singleton
    fun provideCoroutineScope(): CoroutineScope {
        val supervisorJob = SupervisorJob()
        val ioDispatcher = Dispatchers.IO
        return CoroutineScope(supervisorJob + ioDispatcher)
    }

}