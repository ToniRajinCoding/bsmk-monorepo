package com.example.besokmasak.core.data.source.local

import androidx.lifecycle.LiveData
import com.example.besokmasak.core.data.source.local.entity.RecipeEntity
import com.example.besokmasak.core.data.source.local.room.RecipeDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val recipeDao: RecipeDao) {

    //deleted because Im using hilt
//    companion object {
//        private var instance: LocalDataSource? = null
//
//        fun getInstance(recipeDao: RecipeDao): LocalDataSource =
//            instance ?: synchronized(this) {
//                instance ?: LocalDataSource(recipeDao)
//            }
//    }

    fun getFavoritedRecipe(): Flow<List<RecipeEntity>> = recipeDao.getFavoritedRecipe()

    suspend fun insertFavoriteRecipe(recipe: RecipeEntity) = recipeDao.insertFavoritedRecipe(recipe)

    fun deleteFavoriteRecipe(recipe: RecipeEntity) = recipeDao.deleteFavoritedRecipe(recipe)

    fun updateFavoritedRecipe(recipe: RecipeEntity) = recipeDao.updateFavoritedRecipe(recipe)


}