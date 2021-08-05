package com.droidtechlab.yahooapi.data.network

import io.reactivex.Single
import retrofit2.http.GET

interface APIEndPoints {

    // Assigning default value here
    @GET("/sifeeds/cricket/live/json/sapk01222019186652.json")
    fun getApiResponse(): Single<Any>
}