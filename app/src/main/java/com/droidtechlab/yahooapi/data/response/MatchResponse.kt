package com.droidtechlab.yahooapi.data.response

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class MatchResponse(
    @SerializedName("Matchdetail") var matchDetails: MatchDetail? = null,
    @SerializedName("Nuggets") val nuggets: List<String>? = null,
    @SerializedName("Innings") val innings: List<Innings>? = null,
    @SerializedName("Teams") var teams: HashMap<String, Teams>? = null,
    @SerializedName("Notes") val notes: HashMap<String, List<String>>? = null,
) : Parcelable

@Parcelize
data class MatchDetail(
    @SerializedName("Team_Home") var teamHome: String? = null,
    @SerializedName("Team_Away") var teamAway: String? = null,
    @SerializedName("Match") var match: Match? = null,
    @SerializedName("Series") var series: Series? = null,
    @SerializedName("Venue") var venue: Venue? = null,
    @SerializedName("Officials") var officials: Officials? = null,
    @SerializedName("Weather") var weather: String? = null,
    @SerializedName("Tosswonby") var tossWonBy: String? = null,
    @SerializedName("Status") var status: String? = null,
    @SerializedName("Status_Id") var statusId: String? = null,
    @SerializedName("Player_Match") var playerMatch: String? = null,
    @SerializedName("Result") var result: String? = null,
    @SerializedName("Winningteam") var winningTeam: String? = null,
    @SerializedName("Winmargin") var winMargin: String? = null,
    @SerializedName("Equation") var equation: String? = null
) : Parcelable

@Parcelize
data class Match(
    @SerializedName("Livecoverage") var liveCoverage: String? = null,
    @SerializedName("Id") var id: String? = null,
    @SerializedName("Code") var code: String? = null,
    @SerializedName("League") var leauge: String? = null,
    @SerializedName("Number") var number: String? = null,
    @SerializedName("Type") var type: String? = null,
    @SerializedName("Date") var date: String? = null,
    @SerializedName("Time") var time: String? = null,
    @SerializedName("Offset") var offset: String? = null,
    @SerializedName("Daynight") var dayNight: String? = null,
) : Parcelable

@Parcelize
data class Series(
    @SerializedName("Id") var id: String? = null,
    @SerializedName("Name") var name: String? = null,
    @SerializedName("Status") var status: String? = null,
    @SerializedName("Tour") var tour: String? = null,
    @SerializedName("Tour_Name") var tourName: String? = null,
) : Parcelable

@Parcelize
data class Venue(
    @SerializedName("Id") var id: String? = null,
    @SerializedName("Name") var name: String? = null
) : Parcelable

@Parcelize
data class Officials(
    @SerializedName("Umpires") var umpires: String? = null,
    @SerializedName("Referee") var referee: String? = null,
) : Parcelable

@Parcelize
data class Innings(
    @SerializedName("Number") var number: String? = null,
    @SerializedName("Battingteam") var battingTeam: String? = null,
    @SerializedName("Total") var total: String? = null,
    @SerializedName("Wickets") var wickets: String? = null,
    @SerializedName("Overs") var overs: String? = null,
    @SerializedName("Runrate") var runRate: String? = null,
    @SerializedName("Byes") var byes: String? = null,
    @SerializedName("Legbyes") var legByes: String? = null,
    @SerializedName("Wides") var wides: String? = null,
    @SerializedName("Noballs") var noBalls: String? = null,
    @SerializedName("Penalty") var penalty: String? = null,
    @SerializedName("AllottedOvers") var allotedOvers: String? = null,
    @SerializedName("Batsmen") var batsmenObj: List<BatsmenObj>? = null,
    @SerializedName("Partnership_Current") var currentPartnership: CurrentPartnerShip? = null,
    @SerializedName("Bowlers") var bowlersObj: List<BowlersObj>? = null,
    @SerializedName("FallofWickets") var fallOfWickets: List<FallofWickets>? = null,
    @SerializedName("PowerPlay") var powerPlay: PowerPlay? = null,
) : Parcelable

@Parcelize
data class BatsmenObj(
    @SerializedName("Batsman") var batsman: String? = null,
    @SerializedName("Runs") var runs: String? = null,
    @SerializedName("Balls") var balls: String? = null,
    @SerializedName("Fours") var fours: String? = null,
    @SerializedName("Sixes") var sixes: String? = null,
    @SerializedName("Dots") var dots: String? = null,
    @SerializedName("Strikerate") var strikeRate: String? = null,
    @SerializedName("Dismissal") var dismissal: String? = null,
    @SerializedName("Howout") var howOut: String? = null,
    @SerializedName("Bowler") var bowler: String? = null,
    @SerializedName("Fielder") var fielder: String? = null,
) : Parcelable

@Parcelize
data class CurrentPartnerShip(
    @SerializedName("Runs") var runs: String? = null,
    @SerializedName("Balls") var balls: String? = null,
    @SerializedName("Batsman") var partnerBatsmen: List<PartnerBatsmen>? = null,
) : Parcelable

@Parcelize
data class PartnerBatsmen(
    @SerializedName("Runs") var runs: String? = null,
    @SerializedName("Balls") var balls: String? = null,
    @SerializedName("Batsman") var batsmanId: String? = null
) : Parcelable

@Parcelize
data class BowlersObj(
    @SerializedName("Bowler") var bowler: String? = null,
    @SerializedName("Overs") var overs: String? = null,
    @SerializedName("Runs") var runs: String? = null,
    @SerializedName("Maidens") var maidens: String? = null,
    @SerializedName("Wickets") var wickets: String? = null,
    @SerializedName("Economyrate") var economyRate: String? = null,
    @SerializedName("Noballs") var noBalls: String? = null,
    @SerializedName("Wides") var wides: String? = null,
    @SerializedName("Dots") var dots: String? = null,
) : Parcelable

@Parcelize
data class FallofWickets(
    @SerializedName("Overs") var overs: String? = null,
    @SerializedName("Score") var scores: String? = null,
    @SerializedName("Batsman") var batsmanId: String? = null
) : Parcelable

@Parcelize
data class PowerPlay(
    @SerializedName("PP1") var pp1: String? = null,
    @SerializedName("PP2") var pp2: String? = null,
) : Parcelable

//@Parcelize
//data class Notes(
//    @SerializedName("1") var first: List<String>? = null,
//    @SerializedName("2") var second: List<String>? = null,
//) : Parcelable

@Parcelize
data class Teams(
    @SerializedName("Name_Full") var nameFull: String? = null,
    @SerializedName("Name_Short") var nameShort: String? = null,
    @SerializedName("Players") var players: HashMap<String,  Players>? = null,
) : Parcelable

@Parcelize
data class Players(
    @SerializedName("Position") var position: String? = null,
    @SerializedName("Name_Full") var nameFull: String? = null,
    @SerializedName("Iskeeper") var isKeeper: Boolean = false,
    @SerializedName("Batting") var batting: Batting? = null,
    @SerializedName("Bowling") var bowling: Bowling? = null,
) : Parcelable

@Parcelize
data class Batting(
    @SerializedName("Style") var style: String? = null,
    @SerializedName("Average") var average: String? = null,
    @SerializedName("Strikerate") var strikeRate: String? = null,
    @SerializedName("Runs") var runs: String? = null,
) : Parcelable


@Parcelize
data class Bowling(
    @SerializedName("Style") var style: String? = null,
    @SerializedName("Average") var average: String? = null,
    @SerializedName("Economyrate") var economyRate: String? = null,
    @SerializedName("Wickets") var wickets: String? = null,
) : Parcelable



