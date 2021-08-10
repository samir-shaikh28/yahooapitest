package com.droidtechlab.yahooapi.data.db.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.droidtechlab.yahooapi.data.db.TABLE_MATCH_ENTITY
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = TABLE_MATCH_ENTITY)
data class MatchInfoEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "_id") var id: Long,
    @ColumnInfo(name = "weather") var weather: String?,
    @ColumnInfo(name = "status") var status: String?,
    @ColumnInfo(name = "league") var league: String?,
    @ColumnInfo(name = "series") var series: String?,
    @ColumnInfo(name = "result") var result: String?,
    @ColumnInfo(name = "venue") var venue: String?,
    @ColumnInfo(name = "umpires") var umpires: String?,
    @ColumnInfo(name = "referee") var referee: String?,
    @ColumnInfo(name = "man_of_match") var manOfMatch: String?,
    @ColumnInfo(name = "date_time") var dateTime: String?,
    @ColumnInfo(name = "toss_won_by") var tossWonBy: String?,
) : Parcelable


