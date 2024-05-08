package com.example.besokmasak.model.response

import android.os.Parcelable

@kotlinx.parcelize.Parcelize
data class RecipeResponse(
    val recipes: List<Recipe>
) : Parcelable

@kotlinx.parcelize.Parcelize
data class Recipe(
    val ingredients: List<String>,
    val instructions: List<String>,
    val recipe_name: String
) : Parcelable