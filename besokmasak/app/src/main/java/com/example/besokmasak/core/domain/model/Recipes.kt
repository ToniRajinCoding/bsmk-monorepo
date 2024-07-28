package com.example.besokmasak.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable


data class Recipes(
    val recipe_name: String,
    val ingredients: List<RecipesIngredients>,
    val instructions: List<String>,
    var isFavorited : Boolean = false
) : Serializable


data class RecipesIngredients(
    val ingredient:String,
    val quantity: String
) : Serializable