package com.example.besokmasak.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipes(
    val recipe_name: String,
    val ingredients: List<String>,
    val instructions: List<String>,
    var isFavorited : Boolean = false
) : Parcelable