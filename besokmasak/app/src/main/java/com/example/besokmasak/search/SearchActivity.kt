package com.example.besokmasak.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.besokmasak.databinding.ActivitySearchBinding
import com.example.besokmasak.searchresult.RecipeResultActivity
import com.example.besokmasak.ui.RecipeViewModel

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding

    //private val viewModel by lazy { ViewModelProvider(this).get(RecipeViewModel::class.java) }

    //private val viewModel : SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val ingredients = binding.etMaterial.text.toString()
        val method = binding.etMasakType.text.toString()
        val intent = Intent(applicationContext, RecipeResultActivity::class.java)

        binding.btnSubmit.setOnClickListener {
            intent.putExtra("ingredients", ingredients)
            intent.putExtra("method", method)
            startActivity(intent)
        }
    }

//    private fun askRecipe(ingredients:String, method:String){
//        val gson = Gson()
//        val apiService = RetrofitClient.apiService
//        val requestBody = RecipeRequest(
//            ingredients = ingredients,
//            method = method
//        )
//
//        val call = apiService.createQuery(requestBody)
//
//        call.enqueue(object : Callback<RecipeResponse>{
//            override fun onResponse(call: Call<RecipeResponse>, response: Response<RecipeResponse>) {
//                if(response.isSuccessful){
//                    val intent = Intent(applicationContext, RecipeResultActivity::class.java)
//                    val responseString = gson.toJson(response.body())
//                    intent.putExtra("RecipeResponse", responseString)
//                    intent.putExtra("ingredients", ingredients)
//                    intent.putExtra("method", method)
//                    Log.d("hasil jsong: ", responseString)
//                    startActivity(intent)
//                }else{
//                    val errorBody = response.errorBody()?.string()
//                    Log.e("error", "API Error: $errorBody")
//                }
//            }
//            override fun onFailure(call: Call<RecipeResponse>, throwable: Throwable) {
//                Log.e("error", "error because: " + throwable.message)
//            }
//
//        })
//
//
//    }


}