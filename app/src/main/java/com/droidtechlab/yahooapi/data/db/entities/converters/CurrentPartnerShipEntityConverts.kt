package com.droidtechlab.yahooapi.data.db.entities.converters

import androidx.room.TypeConverter
import com.droidtechlab.yahooapi.data.db.entities.CurrentPartnerShipEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object CurrentPartnerShipEntityConverts {
    private val gson = Gson()
    private val type = object : TypeToken<CurrentPartnerShipEntity>() {}.type

    @TypeConverter
    @JvmStatic
    fun stringToNestedData(json: String?): CurrentPartnerShipEntity? {
        return if (json == null) {
            null
        } else gson.fromJson<CurrentPartnerShipEntity>(json, type)
    }

    @TypeConverter
    @JvmStatic
    fun nestedDataToString(nestedData: CurrentPartnerShipEntity?): String? {
        return if (nestedData == null) {
            null
        } else gson.toJson(nestedData, type)
    }
}