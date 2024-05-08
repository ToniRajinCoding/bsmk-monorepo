package com.example.besokmasak.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        val recipeResponse : RecipeResponse? = intent.getParcelableExtra("recipeResponse", RecipeResponse::class.java)
        val listOfRecipe = recipeResponse?.recipes ?: emptyList()

        val recipeResultAdapter = RecipeResultAdapter(listOfRecipe)
        binding.rvRecipeDetail.adapter = recipeResultAdapter

    }
}