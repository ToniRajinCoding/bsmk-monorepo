package com.example.besokmasak.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe")
data class RecipeEntity(

    @PrimaryKey
    @ColumnInfo(name = "recipe_name")
    var recipe_name: String,

    @ColumnInfo(name = "ingredients")
    var ingredients: List<String>,

    @ColumnInfo(name = "instructions")
    var instructions: List<String>,

    @ColumnInfo(name = "isFavorited")
    var isFavorited: Boolean?


)