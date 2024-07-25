package com.example.besokmasak.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipes(
    val recipe_name: String,
    val ingredients: List<RecipesIngredients>,
    val instructions: List<String>,
    var isFavorited : Boolean = false
) : Parcelable

@Parcelize
data class RecipesIngredients(
    val ingredient:String,
    val quantity: String
) : Parcelable