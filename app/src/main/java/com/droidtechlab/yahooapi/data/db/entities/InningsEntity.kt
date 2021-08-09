package com.droidtechlab.yahooapi.data.db.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.droidtechlab.yahooapi.data.db.TABLE_INNINGS_ENTITY
import com.droidtechlab.yahooapi.data.db.entities.converters.BatsmanInningsEntityConverts
import com.droidtechlab.yahooapi.data.db.entities.converters.BowlerInningsEntityConverts
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = TABLE_INNINGS_ENTITY)
data class InningsEntity(
    @PrimaryKey(autoGenerate = true)  var _id: Long,
    @ColumnInfo(name = "batting_team") var battingTeam: String?,
    @ColumnInfo(name = "number") var number: String?,
    @ColumnInfo(name = "total") var total: String?,
    @ColumnInfo(name = "wickets") var wickets: String?,
    @ColumnInfo(name = "overs") var overs: String?,
    @ColumnInfo(name = "run_rate") var runRate: String?,
    @ColumnInfo(name = "byes") var byes: String?,
    @ColumnInfo(name = "legbyes") var legByes: String?,
    @ColumnInfo(name = "wides") var wides: String?,
    @ColumnInfo(name = "no_balls") var noBalls: String?,
    @ColumnInfo(name = "penalty") var penalty: String?,
    @ColumnInfo(name = "allotted_overs") var allotedOvers: String?,
    @ColumnInfo(name = "batsmen") @TypeConverters(BatsmanInningsEntityConverts::class) var batsmanList: List<BatsmenInInningsEntity>?,
    @ColumnInfo(name = "bowlers") @TypeConverters(BowlerInningsEntityConverts::class) var bowlerList: List<BowlerInInningsEntity>?,
    @ColumnInfo(name = "partnership_current") var currentartnerShip: CurrentPartnerShipEntity?,
    @ColumnInfo(name = "power_play") var powerPlay: PowerPlayEntity?,
) : Parcelable

@Parcelize
data class BatsmenInInningsEntity(
    @ColumnInfo(name = "batsman") var batsman: String? = null,
    @ColumnInfo(name = "runs") var runs: String? = null,
    @ColumnInfo(name = "balls") var balls: String? = null,
    @ColumnInfo(name = "fours") var fours: String? = null,
    @ColumnInfo(name = "sixes") var sixes: String? = null,
    @ColumnInfo(name = "dots") var dots: String? = null,
    @ColumnInfo(name = "strike_rate") var strikeRate: String? = null,
    @ColumnInfo(name = "dismissal") var dismissal: String? = null,
    @ColumnInfo(name = "howout") var howOut: String? = null,
    @ColumnInfo(name = "bowler") var bowler: String? = null,
    @ColumnInfo(name = "fielder") var fielder: String? = null,
) : Parcelable

@Parcelize
data class BowlerInInningsEntity(
    @ColumnInfo(name = "bowler") var bowler: String? = null,
    @ColumnInfo(name = "overs") var overs: String? = null,
    @ColumnInfo(name = "runs") var runs: String? = null,
    @ColumnInfo(name = "maidens") var maidens: String? = null,
    @ColumnInfo(name = "Wickets") var wickets: String? = null,
    @ColumnInfo(name = "economy_rate") var economyRate: String? = null,
    @ColumnInfo(name = "no_balls") var noBalls: String? = null,
    @ColumnInfo(name = "wides") var wides: String? = null,
    @ColumnInfo(name = "dots") var dots: String? = null,
) : Parcelable

@Parcelize
data class CurrentPartnerShipEntity(
    @ColumnInfo(name = "runs") var runs: String? = null,
    @ColumnInfo(name = "balls") var balls: String? = null,
    @ColumnInfo(name = "batsman") var partnerBatsmen: List<PartnerBatsmenEntity>? = null,
) : Parcelable

@Parcelize
data class PartnerBatsmenEntity(
    @ColumnInfo(name = "Rruns") var runs: String? = null,
    @ColumnInfo(name = "balls") var balls: String? = null,
    @ColumnInfo(name = "batsman") var batsmanId: String? = null
) : Parcelable

@Parcelize
data class PowerPlayEntity(
    @ColumnInfo(name = "pp1") var pp1: String? = null,
    @ColumnInfo(name = "pp2") var pp2: String? = null,
) : Parcelable

