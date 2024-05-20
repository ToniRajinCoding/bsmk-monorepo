package com.example.besokmasak.viewmodel

import android.util.Log
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

    private val _listOfRecipe = MutableLiveData<List<Recipe>>()
    //val listOfRecipe : LiveData<List<Recipe>> = _listOfRecipe

    private val _ingredients = MutableLiveData<String>()
    private val _method = MutableLiveData<String>()

    fun setInputs(ingredient:String, method:String){
        _ingredients.postValue(ingredient)
        _method.postValue(method)
    }

    fun askRecipe() {
        val apiService = RetrofitClient.apiService
        val requestBody = RecipeRequest(
            ingredients = _ingredients.value!!,
            method = _method.value!!
        )

        val call = apiService.createQuery(requestBody)

        call.enqueue(object : Callback<RecipeResponse> {
            override fun onResponse(
                call: Call<RecipeResponse>,
                response: Response<RecipeResponse>
            ){
                if (response.isSuccessful) {
                    val listOfRecipe = response.body()?.recipes ?: emptyList()
                    if (listOfRecipe.isEmpty()) Log.e("error", "Fatal Error! Empty List")
                    viewModelScope.launch {
                         _listOfRecipe.postValue(listOfRecipe)
                    }
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