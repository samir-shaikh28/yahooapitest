package com.droidtechlab.yahooapi.data.db.entities.converters

import androidx.room.TypeConverter
import com.droidtechlab.yahooapi.data.db.entities.BattingEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object BattingEntityConverters {
    private val gson = Gson()
    private val type = object : TypeToken<BattingEntity>() {}.type

    @TypeConverter
    @JvmStatic
    fun stringToNestedData(json: String?): BattingEntity? {
        return if (json == null) {
            null
        } else gson.fromJson<BattingEntity>(json, type)
    }

    @TypeConverter
    @JvmStatic
    fun nestedDataToString(nestedData: BattingEntity?): String? {
        return if (nestedData == null) {
            null
        } else gson.toJson(nestedData, type)
    }
}