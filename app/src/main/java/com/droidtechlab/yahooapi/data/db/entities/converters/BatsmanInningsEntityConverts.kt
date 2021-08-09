package com.droidtechlab.yahooapi.data.db.entities.converters

import androidx.room.TypeConverter
import com.droidtechlab.yahooapi.data.db.entities.BatsmenInInningsEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object BatsmanInningsEntityConverts {
    private val gson = Gson()
    private val type = object : TypeToken<List<BatsmenInInningsEntity>>() {}.type

    @TypeConverter
    @JvmStatic
    fun stringToNestedData(json: String?): List<BatsmenInInningsEntity>? {
        return if (json == null) {
            null
        } else gson.fromJson<List<BatsmenInInningsEntity>>(json, type)
    }

    @TypeConverter
    @JvmStatic
    fun nestedDataToString(nestedData: List<BatsmenInInningsEntity>?): String? {
        return if (nestedData == null) {
            null
        } else gson.toJson(nestedData, type)
    }
}