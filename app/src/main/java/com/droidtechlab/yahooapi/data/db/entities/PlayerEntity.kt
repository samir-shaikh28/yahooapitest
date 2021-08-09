package com.droidtechlab.yahooapi.data.db.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.droidtechlab.yahooapi.data.db.TABLE_PLAYER_ENTITY
import com.droidtechlab.yahooapi.data.db.entities.converters.BattingEntityConverters
import com.droidtechlab.yahooapi.data.db.entities.converters.BowlingEntityConverters
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = TABLE_PLAYER_ENTITY)
data class PlayerEntity(
    @PrimaryKey @ColumnInfo(name = "_id") var _id: Int,
    @ColumnInfo(name = "full_name") var fullName: String?,
    @ColumnInfo(name = "position") var position: String?,
    @ColumnInfo(name = "batting") @TypeConverters(BattingEntityConverters::class) var battingInfo: BattingEntity?,
    @ColumnInfo(name = "bowling") @TypeConverters(BowlingEntityConverters::class) var bowlingInfo: BowlingEntity?,
    @ColumnInfo(name = "team_id") var teamId: String?,
    @ColumnInfo(name = "team_full_name") var teamFullName: String?,
    @ColumnInfo(name = "team_short_name") var teamShortName: String?,
) : Parcelable


@Parcelize
data class BattingEntity(
    @ColumnInfo(name = "style") var style: String?,
    @ColumnInfo(name = "average") var average: String?,
    @ColumnInfo(name = "strike_rate") var strikeRate: String?,
    @ColumnInfo(name = "runs") var runs: String?,
) : Parcelable


@Parcelize
data class BowlingEntity(
    @ColumnInfo(name = "style") var style: String?,
    @ColumnInfo(name = "average") var average: String?,
    @ColumnInfo(name = "economy_rate") var economyRate: String?,
    @ColumnInfo(name = "wickets") var wickets: String?,
) : Parcelable




