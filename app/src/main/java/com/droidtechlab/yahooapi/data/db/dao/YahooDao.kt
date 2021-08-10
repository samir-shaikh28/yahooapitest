package com.droidtechlab.yahooapi.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.droidtechlab.yahooapi.data.db.*
import com.droidtechlab.yahooapi.data.db.entities.CommentaryEntity
import com.droidtechlab.yahooapi.data.db.entities.InningsEntity
import com.droidtechlab.yahooapi.data.db.entities.MatchInfoEntity
import com.droidtechlab.yahooapi.data.db.entities.PlayerEntity
import io.reactivex.Completable

@Dao
interface YahooDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlayerData(playerEntity: PlayerEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMatchInfo(matchInfo: MatchInfoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCommentaryData(commentaryEntity: CommentaryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertInningsData(matchInningsEntity: InningsEntity)

    @Query("DELETE FROM $TABLE_COMMENTARY_ENTITY")
    fun  deleteCommentary()

    @Query("DELETE FROM $TABLE_INNINGS_ENTITY")
    fun  deleteInningsData()

    @Query("SELECT * FROM $TABLE_PLAYER_ENTITY")
    fun getAllPlayer(): PlayerEntity

    @Query("SELECT * FROM $TABLE_COMMENTARY_ENTITY")
    fun getCommentaryData(): List<CommentaryEntity>

    @Query("SELECT * FROM $TABLE_MATCH_ENTITY")
    fun getMatchInfo(): MatchInfoEntity

    @Query("SELECT * FROM $TABLE_PLAYER_ENTITY WHERE $TEAM_SHORT_NAME = :teamName")
    fun fetchTeamPlayerSummary(teamName: String): List<PlayerEntity>

    @Query("SELECT DISTINCT $TEAM_SHORT_NAME  FROM $TABLE_PLAYER_ENTITY")
    fun getTeamShortNameList(): List<String>

    @Query("SELECT * FROM $TABLE_INNINGS_ENTITY")
    fun fetchInningsData(): List<InningsEntity>
}