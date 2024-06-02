package com.example.besokmasak.core.domain.usecase

import androidx.lifecycle.LiveData
import com.example.besokmasak.core.data.source.remote.Resource
import com.example.besokmasak.core.data.source.remote.response.Recipe
import com.example.besokmasak.core.domain.repository.IRecipeRepository

class RecipeInteractor(private val recipeRepository: IRecipeRepository): RecipeUseCase {
    override fun getAllRecipe() = recipeRepository.getAllRecipe()

}