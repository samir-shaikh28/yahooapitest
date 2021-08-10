package com.droidtechlab.yahooapi.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.droidtechlab.yahooapi.data.db.entities.MatchInfoEntity
import com.droidtechlab.yahooapi.data.network.Results
import com.droidtechlab.yahooapi.data.repo.DataSourceRepo

class MatchInfoViewModel : ViewModel() {

    val matchInfoEntityLiveData = MutableLiveData<MatchInfoEntity>()

    fun updateUI(matchInfoEntity: MatchInfoEntity) {
        matchInfoEntityLiveData.postValue(matchInfoEntity)
    }
}