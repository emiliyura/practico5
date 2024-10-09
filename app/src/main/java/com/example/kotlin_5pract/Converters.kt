package com.example.kotlin_5pract

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

//Нужно для преобразования списка в строку и обратно

class Converters {
    @TypeConverter
    fun fromString(value: String): List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun toString(list: List<String>): String {
        return Gson().toJson(list)
    }
}
