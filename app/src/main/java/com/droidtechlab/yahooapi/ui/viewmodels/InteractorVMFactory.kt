package com.droidtechlab.yahooapi.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.droidtechlab.yahooapi.data.repo.DataSourceRepo

class InteractorVMFactory internal constructor(private val dataSourceRepo: DataSourceRepo) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return Interactor(dataSourceRepo) as T
    }
}