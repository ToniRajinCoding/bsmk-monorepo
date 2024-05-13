package com.example.besokmasak.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.besokmasak.adapter.RecipeResultAdapter
import com.example.besokmasak.databinding.ActivityRecipeResultBinding
import com.example.besokmasak.model.response.RecipeResponse
import com.google.gson.Gson

class RecipeResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeResultBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gson = Gson()
        val stringResponse = intent.getStringExtra("RecipeResponse")

        if(stringResponse != null){
            val recipeResponse = gson.fromJson(stringResponse, RecipeResponse::class.java)
            val listOfRecipe = recipeResponse?.recipes ?: emptyList()
            Log.d("debug listOfRecipe: ", listOfRecipe.toString())
        }else{
            Log.e("error", "kosong euy")
        }
    }

    fun initialize(){

    }


}