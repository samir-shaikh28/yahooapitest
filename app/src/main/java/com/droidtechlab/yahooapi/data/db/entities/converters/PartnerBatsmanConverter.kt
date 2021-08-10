package com.droidtechlab.yahooapi.data.db.entities.converters

import androidx.room.TypeConverter
import com.droidtechlab.yahooapi.data.db.entities.PartnerBatsmenEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object PartnerBatsmanConverter {
    private val gson = Gson()
    private val type = object : TypeToken< ArrayList<PartnerBatsmenEntity>>() {}.type

    @TypeConverter
    @JvmStatic
    fun stringToNestedData(json: String?): ArrayList<PartnerBatsmenEntity>? {
        return if (json == null) {
            null
        } else gson.fromJson<ArrayList<PartnerBatsmenEntity>>(json, type)
    }

    @TypeConverter
    @JvmStatic
    fun nestedDataToString(nestedData: ArrayList<PartnerBatsmenEntity>?): String? {
        return if (nestedData == null) {
            null
        } else gson.toJson(nestedData, type)
    }
}