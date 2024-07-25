package com.example.besokmasak.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "recipe")
data class RecipeEntity(

    @PrimaryKey
    @ColumnInfo(name = "recipe_name")
    var recipe_name: String,

    @ColumnInfo(name = "ingredients")
    @TypeConverters(Converter::class)
    var ingredients: List<IngredientEntity>,

    @ColumnInfo(name = "instructions")
    @TypeConverters(Converter::class)
    var instructions: List<String>,

    @ColumnInfo(name = "isFavorited")
    var isFavorited: Boolean

)

data class IngredientEntity(
    var ingredient: String,
    var quantity: String
)