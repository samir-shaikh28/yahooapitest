package com.droidtechlab.yahooapi.data.db.entities.converters

import androidx.room.TypeConverter
import com.droidtechlab.yahooapi.data.db.entities.BowlerInInningsEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object BowlerInningsEntityConverts {
    private val gson = Gson()
    private val type = object : TypeToken<List<BowlerInInningsEntity>>() {}.type

    @TypeConverter
    @JvmStatic
    fun stringToNestedData(json: String?): List<BowlerInInningsEntity>? {
        return if (json == null) {
            null
        } else gson.fromJson<List<BowlerInInningsEntity>>(json, type)
    }

    @TypeConverter
    @JvmStatic
    fun nestedDataToString(nestedData: List<BowlerInInningsEntity>?): String? {
        return if (nestedData == null) {
            null
        } else gson.toJson(nestedData, type)
    }
}