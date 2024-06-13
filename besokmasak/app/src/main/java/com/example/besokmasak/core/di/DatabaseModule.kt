package com.example.besokmasak.core.di

import android.content.Context
import androidx.room.Room
import com.example.besokmasak.core.data.source.local.room.RecipeDao
import com.example.besokmasak.core.data.source.local.room.RecipeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) : RecipeDatabase = Room.databaseBuilder(
        context,
        RecipeDatabase::class.java, "recipe.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideRecipeDao(database: RecipeDatabase):RecipeDao = database.recipeDao()

}