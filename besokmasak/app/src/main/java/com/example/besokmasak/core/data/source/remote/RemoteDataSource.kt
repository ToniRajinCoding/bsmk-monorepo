package com.example.besokmasak.core.data.source.remote

import android.util.Log
import com.example.besokmasak.core.data.source.remote.network.ApiResponse
import com.example.besokmasak.core.data.source.remote.network.ApiService
import com.example.besokmasak.core.data.source.remote.request.RecipeRequest
import com.example.besokmasak.core.data.source.remote.response.Recipe
import com.example.besokmasak.core.data.source.remote.response.RecipeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService){

    //gausah karena udah di inject
//    companion object{
//        @Volatile
//        private var instance: RemoteDataSource? = null
//
//        fun getInstance(service: ApiService): RemoteDataSource =
//            instance ?: synchronized(this){
//                instance ?: RemoteDataSource(service)
//            }
//    }

    fun searchQuery(recipeRequest: RecipeRequest) : Flow<ApiResponse<List<Recipe>>> {


        return flow {
            try{
                val response = apiService.createQuery(recipeRequest)
                val recipes = response.recipes
                if(recipes.isNotEmpty()){
                    emit(ApiResponse.Success(recipes))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource Error: ", e.toString())
            }
        }.flowOn(Dispatchers.IO)



//        val call = apiService.createQuery(recipeRequest)
//        call.enqueue(object: retrofit2.Callback<RecipeResponse>{
//            override fun onResponse(call: Call<RecipeResponse>, response: Response<RecipeResponse>) {
//                if (response.isSuccessful) {
//                    val responseRecipeList = response.body()?.recipes ?: emptyList()
//                    Log.d("isi dari response", response.body().toString())
//
//                    if (responseRecipeList.isEmpty()) Log.e("error", "Fatal Error! Empty List")
//                } else {
//                    val errorBody = response.errorBody()?.string()
//                    Log.e("error", "API Error: $errorBody")
//                }
//            }
//            override fun onFailure(call: Call<RecipeResponse>, throwable: Throwable) {
//                Log.e("error", "error because: " + throwable.message)
//            }
//        })

    }
}

