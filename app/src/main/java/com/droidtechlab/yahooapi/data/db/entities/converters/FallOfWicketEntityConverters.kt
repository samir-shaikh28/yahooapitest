package com.droidtechlab.yahooapi.data.db.entities.converters

import androidx.room.TypeConverter
import com.droidtechlab.yahooapi.data.db.entities.FallOfWicketEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken



object FallOfWicketEntityConverters {
    private val gson = Gson()
    private val type = object : TypeToken<ArrayList<FallOfWicketEntity>>() {}.type

    @TypeConverter
    @JvmStatic
    fun stringToNestedData(json: String?): ArrayList<FallOfWicketEntity>? {
        return if (json == null) {
            null
        } else gson.fromJson<ArrayList<FallOfWicketEntity>>(json, type)
    }

    @TypeConverter
    @JvmStatic
    fun nestedDataToString(nestedData: ArrayList<FallOfWicketEntity>?): String? {
        return if (nestedData == null) {
            null
        } else gson.toJson(nestedData, type)
    }
}