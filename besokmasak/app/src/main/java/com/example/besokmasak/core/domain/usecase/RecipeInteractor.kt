package com.example.besokmasak.core.domain.usecase

import androidx.lifecycle.LiveData
import com.example.besokmasak.core.data.source.Resource
import com.example.besokmasak.core.data.source.remote.request.RecipeRequest
import com.example.besokmasak.core.data.source.remote.response.Recipe
import com.example.besokmasak.core.domain.model.Recipes
import com.example.besokmasak.core.domain.repository.IRecipeRepository
import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody
import javax.inject.Inject

class RecipeInteractor @Inject constructor(private val recipeRepository: IRecipeRepository): RecipeUseCase {
    override suspend fun searchRecipe(recipeRequest: RecipeRequest)=recipeRepository.searchRecipe(recipeRequest)
    override fun getAllRecipe() = recipeRepository.getAllRecipe()
    override suspend fun updateFavoritedRecipe(recipe: Recipes, state: Boolean) = recipeRepository.updateFavoritedRecipe(recipe,state)

}