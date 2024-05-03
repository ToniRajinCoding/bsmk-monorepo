package com.example.besokmasak.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.besokmasak.api.ApiService
import com.example.besokmasak.api.RetrofitClient
import com.example.besokmasak.databinding.ActivitySearchBinding
import com.example.besokmasak.model.request.RecipeRequest
import com.example.besokmasak.model.response.RecipeResponse
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val ingredients = binding.etMaterial.text.toString()
        val method = binding.etMasakType.text.toString()

        binding.btnSubmit.setOnClickListener {
            askRecipe(ingredients, method)
//            { recipe ->
//                binding.tvResult.text = recipe
//            }
        }
    }

 //callback: (String) -> Unit

    private fun askRecipe(ingredients:String, method:String){
        val gson = Gson()
        val apiService = RetrofitClient.apiService
        val requestBody = RecipeRequest(
            ingredients = ingredients,
            method = method
        )

        val call = apiService.createQuery(requestBody)
        call.enqueue(object : Callback<RecipeResponse>{
            override fun onResponse(call: Call<RecipeResponse>, response: Response<RecipeResponse>) {
                if(response.isSuccessful){
                    Log.d("tag", response.body().toString())
                    val jsonResponse = gson.toJson(response.body())
                    Log.d("debug gson result", jsonResponse.toString())
                    runOnUiThread{
                        binding.tvResult.text = jsonResponse
                    }
                }else{
                    val errorBody = response.errorBody()?.string()
                }
            }
            override fun onFailure(call: Call<RecipeResponse>, throwable: Throwable) {
                Log.d("error", "error because" + throwable.message)
            }

        })


    }


}