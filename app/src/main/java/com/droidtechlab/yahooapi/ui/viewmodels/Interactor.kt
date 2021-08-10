package com.droidtechlab.yahooapi.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.droidtechlab.yahooapi.data.db.entities.CommentaryEntity
import com.droidtechlab.yahooapi.data.db.entities.InningsEntity
import com.droidtechlab.yahooapi.data.db.entities.MatchInfoEntity
import com.droidtechlab.yahooapi.data.db.entities.PlayerEntity
import com.droidtechlab.yahooapi.data.network.Results
import com.droidtechlab.yahooapi.data.repo.DataSourceRepo
import com.droidtechlab.yahooapi.data.response.MatchDetail
import com.droidtechlab.yahooapi.data.response.MatchResponse
import com.droidtechlab.yahooapi.data.response.YahooResponseHandler

class Interactor(private val repo: DataSourceRepo): ViewModel() {

    // Fetch API Response
    fun fetchApiResponse(): MutableLiveData<Results<Boolean>> {
        return repo.fetchApiResponse()
    }

    fun fetchMatchInfo(): MutableLiveData<Results<MatchInfoEntity>> {
        return repo.fetchMatchInfo()
    }

    fun fetchCommentary(): MutableLiveData<Results<List<CommentaryEntity>>> {
        return repo.fetchCommentary()
    }

    fun  fetchTeamName(): MutableLiveData<Results<List<String>>> {
        return repo.fetchTeamName()
    }

    fun  fetchTeamPlayersSummary(teamName: String): MutableLiveData<Results<List<PlayerEntity>>> {
        return repo.fetchTeamPlayersSummary(teamName)
    }

    fun  fetchInningsData(): MutableLiveData<Results<List<InningsEntity>>> {
        return repo.fetchInningsData()
    }

    override fun onCleared() {
        super.onCleared()
        repo.dispose()
    }

}