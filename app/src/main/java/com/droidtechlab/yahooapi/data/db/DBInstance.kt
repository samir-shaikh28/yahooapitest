package com.droidtechlab.yahooapi.data.db

import android.content.Context
import androidx.room.Room

object DBInstance {

    const val TAG = "DBInstance"
    private const val YAHOO_DB = "yahoo.db"
    private lateinit var databaseClient: DB

    @JvmStatic
    fun getDbInstance(context: Context): DB {
        if (!DBInstance::databaseClient.isInitialized) {
            databaseClient = Room.databaseBuilder(context, DB::class.java, YAHOO_DB)
                .fallbackToDestructiveMigration()
                .build()
        }
        return databaseClient
    }
}