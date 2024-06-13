package com.example.besokmasak.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.besokmasak.core.data.source.local.entity.RecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipe")
    fun getFavoritedRecipe() : Flow<List<RecipeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoritedRecipe(recipes : RecipeEntity)

    @Update
    fun updateFavoritedRecipe(recipe: RecipeEntity)

    @Delete
    fun deleteFavoritedRecipe(recipe: RecipeEntity)
}