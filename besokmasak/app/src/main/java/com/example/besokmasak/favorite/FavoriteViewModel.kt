package com.example.besokmasak.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.besokmasak.core.domain.model.Recipes
import com.example.besokmasak.core.domain.usecase.RecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(recipeUseCase: RecipeUseCase) : ViewModel(){

val favoriteRecipe = recipeUseCase.getAllRecipe().asLiveData()

}