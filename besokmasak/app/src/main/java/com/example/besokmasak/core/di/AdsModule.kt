package com.example.besokmasak.core.di

import android.app.Activity
import android.content.Context
import com.example.besokmasak.utils.AdmobManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
class AdsModule {

    @Provides
    fun provideAdmobManager(@ApplicationContext appContext: Context) : AdmobManager{
        return AdmobManager(appContext)
    }

}