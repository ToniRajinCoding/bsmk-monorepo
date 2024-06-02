package com.example.besokmasak.core.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.besokmasak.core.data.source.remote.network.ApiService
import com.example.besokmasak.core.data.source.remote.request.RecipeRequest
import com.example.besokmasak.core.data.source.remote.response.RecipeResponse
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class RemoteDataSource private constructor(private val apiService: ApiService){
    companion object{
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this){
                instance ?: RemoteDataSource(service)
            }
    }

    fun getAllRecipe(recipeRequest: RecipeRequest){

        val call = apiService.createQuery(recipeRequest)

        call.enqueue(object: retrofit2.Callback<RecipeResponse>{

            override fun onResponse(call: Call<RecipeResponse>, response: Response<RecipeResponse>) {
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

