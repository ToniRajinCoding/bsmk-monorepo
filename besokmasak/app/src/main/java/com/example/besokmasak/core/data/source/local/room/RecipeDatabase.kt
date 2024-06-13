package com.example.besokmasak.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.besokmasak.core.data.source.local.entity.RecipeEntity

@Database(entities = [RecipeEntity::class], version = 1, exportSchema = false)
abstract class RecipeDatabase : RoomDatabase() {

    abstract fun recipeDao() : RecipeDao

//    companion object{
//        @Volatile
//        private var INSTANCE : RecipeDatabase? = null
//
//        fun getInstance(context: Context) : RecipeDatabase =
//            INSTANCE ?: synchronized(this){
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    RecipeDatabase::class.java,
//                    "recipe.db"
//                )
//                    .fallbackToDestructiveMigration()
//                    .build()
//                INSTANCE = instance
//                instance
//            }
//    }

}