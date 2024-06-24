package com.example.besokmasak.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.besokmasak.core.domain.model.Recipes
import com.example.besokmasak.core.domain.usecase.RecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val recipeUseCase: RecipeUseCase) : ViewModel(){

val favoriteRecipe = recipeUseCase.getAllRecipe().asLiveData()

    fun updateFavoriteState(recipe:Recipes, state: Boolean){
        viewModelScope.launch {
            recipeUseCase.updateFavoritedRecipe(recipe,state)
        }
    }

}