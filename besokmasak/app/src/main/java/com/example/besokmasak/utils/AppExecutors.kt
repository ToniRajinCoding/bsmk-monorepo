package com.example.besokmasak.utils

import android.os.Looper
import androidx.annotation.VisibleForTesting
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject

class AppExecutors @Inject constructor(
    private val diskIo: Executor,
    private val networkIO: Executor,
    private val mainThread: Executor
) {

    companion object {
        const val THREAD_COUNT = 3
    }

    fun diskIO(): Executor = diskIo

    fun networkID(): Executor = networkIO

    fun mainThread(): Executor = mainThread


}