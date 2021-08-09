package com.droidtechlab.yahooapi.data.db.dao

import androidx.room.*
import com.droidtechlab.yahooapi.data.db.*
import com.droidtechlab.yahooapi.data.db.entities.CommentaryEntity
import com.droidtechlab.yahooapi.data.db.entities.InningsEntity
import com.droidtechlab.yahooapi.data.db.entities.MatchInfoEntity
import com.droidtechlab.yahooapi.data.db.entities.PlayerEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface YahooDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlayerData(playerEntity: PlayerEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMatchInfo(matchInfo: MatchInfoEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCommentaryData(commentaryEntity: CommentaryEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertInningsData(matchInningsEntity: InningsEntity): Completable




    @Query("SELECT * FROM $TABLE_PLAYER_ENTITY")
    fun getAllPlayer(): PlayerEntity

    @Query("SELECT * FROM $TABLE_COMMENTARY_ENTITY")
    fun getCommentaryData(): CommentaryEntity

    @Query("SELECT * FROM $TABLE_MATCH_ENTITY")
    fun getMatchInfo(): MatchInfoEntity

    @Query("SELECT * FROM $TABLE_PLAYER_ENTITY WHERE $TEAM_ID = :teamId")
    fun getPlayerOfTeam(teamId: String): PlayerEntity
}