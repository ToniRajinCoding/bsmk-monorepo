package com.example.besokmasak.model.response

import android.os.Parcelable

data class RecipeResponse(
    val recipes: List<Recipe>
)

data class Recipe(
    val ingredients: List<String>,
    val instructions: List<String>,
    val recipe_name: String
)