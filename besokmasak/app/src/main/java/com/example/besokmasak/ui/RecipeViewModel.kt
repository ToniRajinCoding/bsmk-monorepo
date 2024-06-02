package com.example.besokmasak.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.besokmasak.core.data.source.remote.network.ApiConfig
import com.example.besokmasak.core.data.source.remote.request.RecipeRequest
import com.example.besokmasak.core.data.source.remote.response.Recipe
import com.example.besokmasak.core.data.source.remote.response.RecipeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeViewModel : ViewModel() {

    //private val _listOfRecipe = MutableLiveData<List<Recipe>>()
    //val listOfRecipe : LiveData<List<Recipe>> = _listOfRecipe

    private var _listOfRecipe = MutableLiveData<List<Recipe>>()
    val listOfRecipe : LiveData<List<Recipe>> = _listOfRecipe

    private val _ingredients = MutableLiveData<String>()
    val ingredients : LiveData<String> = _ingredients

    private val _method = MutableLiveData<String>()
    val method : LiveData<String> = _method

    fun setInputs(ingredientInput:String, methodInput:String){
        _ingredients.value = ingredientInput
        _method.value = methodInput
    }

}