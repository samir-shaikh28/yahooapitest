package com.droidtechlab.yahooapi

import android.app.Application
import android.os.StrictMode.VmPolicy

import android.os.StrictMode

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        StrictMode.enableDefaults()

    }
}