package com.example.besokmasak.core.data.source.remote.response

data class RecipeResponse(
    val recipes: List<Recipe>
)

data class Recipe(
    val ingredients: List<String>,
    val instructions: List<String>,
    val recipe_name: String
)