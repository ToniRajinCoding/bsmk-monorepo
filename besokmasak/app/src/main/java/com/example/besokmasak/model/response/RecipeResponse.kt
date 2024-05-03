package com.example.besokmasak.model.response

data class RecipeResponse(
    val recipes: List<Recipe>
)

data class Recipe(
    val ingredients: List<String>,
    val instructions: List<String>,
    val recipe_name: String
)