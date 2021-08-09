package com.droidtechlab.yahooapi.data.response

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

class YahooResponseHandler {

    private var teamHome: String = ""
    private var teamAway: String = ""
    private val gson = Gson()


    fun parseResponse(response: JSONObject, context: Context) {
        val matchDetail = getMatchDetailResponse(response)
        teamAway = matchDetail.teamAway!!
        teamHome = matchDetail.teamHome!!
        val teamAwayResponse = getTeamResponse(response, teamAway)
        val teamHomeResponse = getTeamResponse(response, teamHome)


    }

    private fun getMatchDetailResponse(response: JSONObject): MatchDetail {
        return gson.fromJson<MatchDetail>(
            response.get(MATCH_DETAIL).toString(),
            object : TypeToken<MatchDetail>() {}.type
        )
    }

    private fun getTeamResponse(response: JSONObject, teamAways: String): Teams {
        return gson.fromJson<Teams>(
            response.getJSONObject(TEAMS).getJSONObject(teamAways).toString(),
            object : TypeToken<Teams>() {}.type
        )
    }

    companion object {
        const val TEAMS = "Teams"
        const val MATCH_DETAIL = "Matchdetail"
    }
}