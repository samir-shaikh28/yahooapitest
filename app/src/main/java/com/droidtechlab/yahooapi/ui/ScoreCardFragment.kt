package com.droidtechlab.yahooapi.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.droidtechlab.yahooapi.R
import com.droidtechlab.yahooapi.data.db.DBInstance
import com.droidtechlab.yahooapi.data.db.dao.YahooDao
import com.droidtechlab.yahooapi.data.db.entities.InningsEntity
import com.droidtechlab.yahooapi.data.network.ApiClient
import com.droidtechlab.yahooapi.data.network.Error
import com.droidtechlab.yahooapi.data.network.Failure
import com.droidtechlab.yahooapi.data.network.Success
import com.droidtechlab.yahooapi.data.repo.DataSourceRepo
import com.droidtechlab.yahooapi.ui.viewholders.AbstractViewHolder
import com.droidtechlab.yahooapi.ui.viewholders.InningViewHolder
import com.droidtechlab.yahooapi.ui.viewmodels.Interactor
import com.droidtechlab.yahooapi.ui.viewmodels.InteractorVMFactory
import com.google.android.material.appbar.AppBarLayout


class ScoreCardFragment : GenericRecyclerFragment(), NavigationListener {

    private lateinit var yahooDao: YahooDao
    private lateinit var interactor: Interactor
    private var toolbar: Toolbar? = null
    private var appBar: AppBarLayout? = null
    private lateinit var navController: NavController


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setStubView(R.layout.lyt_toolbar)

        toolbar = stubView?.findViewById(R.id.toolbar)
        appBar = stubView?.findViewById(R.id.appbar)
        navController = Navigation.findNavController(view)


        yahooDao = DBInstance.getDbInstance(requireContext()).yahooDao()
        interactor = ViewModelProvider(
            this,
            InteractorVMFactory(
                DataSourceRepo(
                    requireActivity(),
                    ApiClient.apiClient!!,
                    yahooDao
                )
            )
        ).get(Interactor::class.java)
        initToolBar()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initToolBar() {
        toolbar?.apply {
            setSupportActionBar(this)
            actionBar?.title = "Innings"
        }
    }


    override fun loadData() {
        if (consumeCallback()) return
        val listOfInnings = mutableListOf<AbstractViewHolder>()

        interactor.fetchInningsData().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Success -> {
                    result.data.forEach {
                        listOfInnings.add(InningViewHolder(it, this@ScoreCardFragment))
                    }
                    updateList(listOfInnings)
                }
                is Failure -> {
                    Log.d("###", "fetch comment failure")
                }
                is Error -> {
                    Log.d("###", "fetch comment error")
                }

            }
        })
    }

    override fun openInningsPagerScreen(inningsEntity: InningsEntity) {
        val bundle = Bundle()
        bundle.putParcelable(INNINGS_DATA, inningsEntity)
        navController.navigate(R.id.action_scoreCardFragment_to_inningsParentFragment, bundle)
    }

    companion object {
        private const val INNINGS_DATA = "innings_data"
    }

}

interface NavigationListener {
    fun openInningsPagerScreen(inningsEntity: InningsEntity)
}