package com.example.besokmasak.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.besokmasak.api.remote.RetrofitClient
import com.example.besokmasak.model.request.RecipeRequest
import com.example.besokmasak.model.response.Recipe
import com.example.besokmasak.model.response.RecipeResponse
import kotlinx.coroutines.launch
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

    fun generateRecipe() {
        val apiService = RetrofitClient.apiService
        val requestBody = RecipeRequest(
            ingredients = ingredients,
            method = method
        )

        Log.d("isi dari ing & met: ", "$ingredients $method")

        val call = apiService.createQuery(requestBody)

        call.enqueue(object : Callback<RecipeResponse> {
            override fun onResponse(
                call: Call<RecipeResponse>,
                response: Response<RecipeResponse>
            ){
                if (response.isSuccessful) {
                    val responseRecipeList = response.body()?.recipes ?: emptyList()
                    Log.d("isi dari response", response.body().toString())
                    if (responseRecipeList.isEmpty()) Log.e("error", "Fatal Error! Empty List")

                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.e("error", "API Error: $errorBody")
                }
            }

            override fun onFailure(call: Call<RecipeResponse>, throwable: Throwable) {
                Log.e("error", "error because: " + throwable.message)
            }

        })
    }


}