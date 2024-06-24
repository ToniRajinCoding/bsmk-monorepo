package com.example.besokmasak.core.data.source.local.entity

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class StringListConverter {

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