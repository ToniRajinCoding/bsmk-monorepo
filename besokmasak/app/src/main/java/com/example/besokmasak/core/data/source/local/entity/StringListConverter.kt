package com.example.besokmasak.core.data.source.local.entity

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class StringListConverter  {

    @Inject lateinit var gson : Gson

    @TypeConverter
    fun fromList(value : List<String>) : String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toList(value : String) : List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value,type)
    }
}