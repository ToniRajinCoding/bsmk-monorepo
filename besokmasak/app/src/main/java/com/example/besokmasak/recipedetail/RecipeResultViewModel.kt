package com.example.besokmasak.recipedetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.besokmasak.core.data.source.Resource
import com.example.besokmasak.core.data.source.remote.request.RecipeRequest
import com.example.besokmasak.core.domain.model.Recipes
import com.example.besokmasak.core.domain.usecase.RecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeResultViewModel @Inject constructor(
    private val recipeUseCase: RecipeUseCase,
    private val viewModelScope : CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
) : ViewModel() {

    val _paginationCounter = MutableLiveData<Int>()
    val paginationCounter : LiveData<Int> = _paginationCounter

    val _recipesLiveData = MutableLiveData<Resource<List<Recipes>>>()
    val recipesLiveData : LiveData<Resource<List<Recipes>>> = _recipesLiveData

    fun searchQuery(ingredients: String, method: String, language: String){
        Log.d("Search Query","Search Query Dijalankan")

        val recipeRequest = RecipeRequest(ingredients = ingredients, method = method, language = language)

        viewModelScope.launch {
            val recipesFlow = recipeUseCase.searchRecipe(recipeRequest)
            recipesFlow.collect{resource ->
                _recipesLiveData.postValue(resource)
            }
        }

    }

    fun updateFavoriteState(recipe:Recipes, state: Boolean){
        viewModelScope.launch {
            recipeUseCase.updateFavoritedRecipe(recipe,state)
        }
    }



}