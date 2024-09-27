package com.example.besokmasak.core.domain.repository

import androidx.lifecycle.LiveData
import com.example.besokmasak.core.data.source.Resource
import com.example.besokmasak.core.data.source.remote.request.RecipeRequest
import com.example.besokmasak.core.domain.model.Recipes
import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody

interface IRecipeRepository {
    suspend fun searchRecipe(recipeRequest: RecipeRequest) : Flow<Resource<List<Recipes>>>
    fun getAllRecipe() : Flow<List<Recipes>>
    suspend fun updateFavoritedRecipe(recipe: Recipes, state: Boolean)
}