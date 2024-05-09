package com.example.besokmasak.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.besokmasak.R
import com.example.besokmasak.adapter.RecipeResultAdapter
import com.example.besokmasak.databinding.ActivityRecipeResultBinding
import com.example.besokmasak.model.response.RecipeResponse

class RecipeResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recipeResponse : RecipeResponse? = intent.getParcelableExtra("RecipeResponse", RecipeResponse::class.java)
        Log.d("debug", recipeResponse.toString())
        val listOfRecipe = recipeResponse?.recipes ?: emptyList()
        Log.d("debug listOfRecipe: ", listOfRecipe.toString())

        val layoutManager = LinearLayoutManager(this)
        val recipeResultAdapter = RecipeResultAdapter(listOfRecipe, layoutManager)
        binding.rvRecipeDetail.adapter = recipeResultAdapter
        binding.rvRecipeDetail.layoutManager = layoutManager
    }
}