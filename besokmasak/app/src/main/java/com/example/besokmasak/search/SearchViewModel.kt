package com.example.besokmasak.search

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
class SearchViewModel @Inject constructor(private val recipeUseCase: RecipeUseCase,
    private val viewModelScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
) : ViewModel() {

    private val _recipesLiveData = MutableLiveData<Resource<List<Recipes>>>()
    val recipesLiveData: LiveData<Resource<List<Recipes>>> = _recipesLiveData

    //val searchRecipes = recipeUseCase.searchRecipe(searchQuery.value!!)

    fun onSearchQuery(ingredients : String, method: String){
        val requestBody = RecipeRequest(
            ingredients = ingredients,
            method = method
        )

        viewModelScope.launch {
            val recipes = recipeUseCase.searchRecipe(requestBody).collect{ resource ->
                _recipesLiveData.value = resource
            }
        }

    }

}