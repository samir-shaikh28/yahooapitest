package com.droidtechlab.yahooapi.data.db.entities.converters

import androidx.room.TypeConverter
import com.droidtechlab.yahooapi.data.db.entities.BowlingEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object BowlingEntityConverters {
    private val gson = Gson()
    private val type = object : TypeToken<BowlingEntity>() {}.type

    @TypeConverter
    @JvmStatic
    fun stringToNestedData(json: String?): BowlingEntity? {
        return if (json == null) {
            null
        } else gson.fromJson<BowlingEntity>(json, type)
    }

    @TypeConverter
    @JvmStatic
    fun nestedDataToString(nestedData: BowlingEntity?): String? {
        return if (nestedData == null) {
            null
        } else gson.toJson(nestedData, type)
    }
}