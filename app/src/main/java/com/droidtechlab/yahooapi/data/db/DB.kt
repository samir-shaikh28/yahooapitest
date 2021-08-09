package com.droidtechlab.yahooapi.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.droidtechlab.yahooapi.data.db.dao.YahooDao
import com.droidtechlab.yahooapi.data.db.entities.CommentaryEntity
import com.droidtechlab.yahooapi.data.db.entities.InningsEntity
import com.droidtechlab.yahooapi.data.db.entities.MatchInfoEntity
import com.droidtechlab.yahooapi.data.db.entities.PlayerEntity
import com.droidtechlab.yahooapi.data.db.entities.converters.*

const val DB_VERSION = 1
const val TABLE_PLAYER_ENTITY = "table_player"
const val TABLE_INNINGS_ENTITY = "table_innings"
const val TABLE_COMMENTARY_ENTITY = "table_commentary"
const val TABLE_MATCH_ENTITY = "match_info_player"
const val TEAM_ID = "team_id"

@Database(
    entities = [PlayerEntity::class, CommentaryEntity::class, MatchInfoEntity::class, InningsEntity::class],
    version = DB_VERSION
)

@TypeConverters(
    BattingEntityConverters::class,
    BowlingEntityConverters::class,
    BatsmanInningsEntityConverts::class,
    BowlerInningsEntityConverts::class,
    CurrentPartnerShipEntityConverts::class,
    PowerPlayEntityConverter::class
)
abstract class DB : RoomDatabase() {
    abstract fun yahooDao(): YahooDao
}