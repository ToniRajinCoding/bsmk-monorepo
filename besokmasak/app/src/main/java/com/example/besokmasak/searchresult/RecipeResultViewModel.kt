package com.example.besokmasak.searchresult

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.besokmasak.core.data.source.Resource
import com.example.besokmasak.core.data.source.remote.request.RecipeRequest
import com.example.besokmasak.core.domain.model.Recipes
import com.example.besokmasak.core.domain.usecase.RecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class RecipeResultViewModel @Inject constructor(
    private val recipeUseCase: RecipeUseCase,
    private val viewModelScope : CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
) : ViewModel() {

    private val _recipesFlow = MutableStateFlow<Flow<Resource<List<Recipes>>>?>(null)
    val recipesFlow : StateFlow<Flow<Resource<List<Recipes>>>?> = _recipesFlow


    fun searchQuery(ingredients: String, method: String){
        val recipeRequest = RecipeRequest(ingredients = ingredients, method = method)

        viewModelScope.launch {
            val recipesFlow = recipeUseCase.searchRecipe(recipeRequest)
            _recipesFlow.value = recipesFlow
        }

//: LiveData<Resource<List<Recipes>>>
//        val recipeList : LiveData<Resource<List<Recipes>>> = LiveData {
//            val recipes = recipeUseCase.searchRecipe(ingredients,method)
//            emit(recipes)
//        }

    }



}