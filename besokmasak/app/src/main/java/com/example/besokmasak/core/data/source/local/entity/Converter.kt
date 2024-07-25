package com.example.besokmasak.core.data.source.local.entity

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {

    @TypeConverter
    fun fromIngredientList(value: List<IngredientEntity>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toIngredientList(value: String): List<IngredientEntity> {
        val listType = object : TypeToken<List<IngredientEntity>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(value : List<String>) : String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toList(value : String) : List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value,type)
    }
}