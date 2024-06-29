package com.example.besokmasak.core.data.source.remote.response

data class RecipeResponse(
    val recipes: List<Recipe>
)

data class Recipe(
    val recipe_name: String,
    val ingredients: List<String>,
    val instructions: List<String>,
)