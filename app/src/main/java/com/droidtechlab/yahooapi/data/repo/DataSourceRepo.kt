package com.droidtechlab.yahooapi.data.repo

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.droidtechlab.yahooapi.data.db.DBHelper
import com.droidtechlab.yahooapi.data.db.dao.YahooDao
import com.droidtechlab.yahooapi.data.db.entities.CommentaryEntity
import com.droidtechlab.yahooapi.data.db.entities.InningsEntity
import com.droidtechlab.yahooapi.data.db.entities.MatchInfoEntity
import com.droidtechlab.yahooapi.data.db.entities.PlayerEntity
import com.droidtechlab.yahooapi.data.network.APIEndPoints
import com.droidtechlab.yahooapi.data.network.Failure
import com.droidtechlab.yahooapi.data.network.Results
import com.droidtechlab.yahooapi.data.network.Success
import com.droidtechlab.yahooapi.data.response.MatchDetail
import com.droidtechlab.yahooapi.data.response.MatchResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

class DataSourceRepo(
    private val context: Context,
    private val retrofit: Retrofit,
    private var dao: YahooDao
) {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()


    fun fetchApiResponse(): MutableLiveData<Results<Boolean>> {
        val liveData: MutableLiveData<Results<Boolean>> =
            MutableLiveData()

        compositeDisposable.add(
            retrofit.create(APIEndPoints::class.java).getApiResponse()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    updateDataBase(it)
                }
                .subscribe({
                    liveData.postValue(Success(true))
                }, { t ->
                    liveData.postValue(Failure(t))
                })
        )
        return liveData
    }

    private fun updateDataBase(it: MatchResponse) {

        Observable.fromCallable {
            val matchInfoEntity =
                DBHelper.toMatchEntity(it.matchDetails ?: MatchDetail())
            // Insert Match Info into DB
            dao.insertMatchInfo(matchInfoEntity)
            // Insert  Comment Data
            it.nuggets?.forEach { comment ->
                dao.insertCommentaryData(CommentaryEntity(0, comment))
            }
            // Insert Player info
            it.teams?.forEach { (teamId, teams) ->
                teams.players?.forEach { (id, player) ->
                    val playerEntity =
                        DBHelper.toPlayerEntity(
                            teams.nameFull,
                            teams.nameShort,
                            teamId,
                            id,
                            player
                        )
                   dao.insertPlayerData(playerEntity)
                }
            }
            // Insert  Innings Data
            it.innings?.forEach {
                val inningsEntity = DBHelper.toInningsEntity(it)
                dao.insertInningsData(inningsEntity)
            }
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()

    }

    fun fetchMatchInfo(): MutableLiveData<Results<MatchInfoEntity>> {
        val liveData: MutableLiveData<Results<MatchInfoEntity>> =
            MutableLiveData()
        compositeDisposable.add(
            Observable.fromCallable {
                dao.getMatchInfo()

            }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    liveData.postValue(Success(response))
                }, { t: Throwable? ->
                    liveData.postValue(
                        Failure(
                            t
                                ?: Throwable("Exception is null")
                        )
                    )
                })
        )
        return liveData
    }

    fun fetchCommentary(): MutableLiveData<Results<List<CommentaryEntity>>> {
        val liveData: MutableLiveData<Results<List<CommentaryEntity>>> = MutableLiveData()
        compositeDisposable.add(
            Observable.fromCallable {
                dao.getCommentaryData()
            }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    liveData.postValue(Success(response))
                }, { t: Throwable? ->
                    liveData.postValue(
                        Failure(
                            t
                                ?: Throwable("Exception is null")
                        )
                    )
                })
        )
        return liveData
    }

    fun fetchTeamName(): MutableLiveData<Results<List<String>>> {
        val liveData: MutableLiveData<Results<List<String>>> = MutableLiveData()
        compositeDisposable.add(
            Observable.fromCallable {
                dao.getTeamShortNameList()
            }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    liveData.postValue(Success(response))
                }, { t: Throwable? ->
                    liveData.postValue(
                        Failure(
                            t
                                ?: Throwable("Exception is null")
                        )
                    )
                })
        )
        return liveData
    }

    fun fetchTeamPlayersSummary(teamName: String): MutableLiveData<Results<List<PlayerEntity>>> {
        val liveData: MutableLiveData<Results<List<PlayerEntity>>> = MutableLiveData()
        compositeDisposable.add(
            Observable.fromCallable {
                dao.fetchTeamPlayerSummary(teamName)
            }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    liveData.postValue(Success(response))
                }, { t: Throwable? ->
                    liveData.postValue(
                        Failure(
                            t
                                ?: Throwable("Exception is null")
                        )
                    )
                })
        )
        return liveData
    }

    fun fetchInningsData(): MutableLiveData<Results<List<InningsEntity>>> {
        val liveData: MutableLiveData<Results<List<InningsEntity>>> = MutableLiveData()
        compositeDisposable.add(
            Observable.fromCallable {
                dao.fetchInningsData()
            }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    liveData.postValue(Success(response))
                }, { t: Throwable? ->
                    liveData.postValue(
                        Failure(
                            t
                                ?: Throwable("Exception is null")
                        )
                    )
                })
        )
        return liveData
    }

    fun dispose() {
        if(!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}