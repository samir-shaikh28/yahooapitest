package com.droidtechlab.yahooapi.data.db.entities.converters

import androidx.room.TypeConverter
import com.droidtechlab.yahooapi.data.db.entities.PowerPlayEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object PowerPlayEntityConverter {
    private val gson = Gson()
    private val type = object : TypeToken<PowerPlayEntity>() {}.type

    @TypeConverter
    @JvmStatic
    fun stringToNestedData(json: String?): PowerPlayEntity? {
        return if (json == null) {
            null
        } else gson.fromJson<PowerPlayEntity>(json, type)
    }

    @TypeConverter
    @JvmStatic
    fun nestedDataToString(nestedData: PowerPlayEntity?): String? {
        return if (nestedData == null) {
            null
        } else gson.toJson(nestedData, type)
    }
}