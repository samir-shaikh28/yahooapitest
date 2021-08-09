package com.droidtechlab.yahooapi.data.network

import com.droidtechlab.yahooapi.data.response.MatchResponse
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface APIEndPoints {

    // Assigning default value here
    @GET("/sifeeds/cricket/live/json/sapk01222019186652.json")
    fun getApiResponse(): Single<MatchResponse>
}