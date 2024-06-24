package com.example.besokmasak.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipes(
    val ingredients: List<String>,
    val instructions: List<String>,
    val recipe_name: String,
    var isFavorited : Boolean = false
) : Parcelable