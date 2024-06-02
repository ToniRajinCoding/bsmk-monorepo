package com.example.besokmasak.core.domain.model

data class Recipes(
    val ingredients: List<String>,
    val instructions: List<String>,
    val recipe_name: String
)