package com.droidtechlab.yahooapi.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.droidtechlab.yahooapi.R
import com.droidtechlab.yahooapi.data.db.DBInstance
import com.droidtechlab.yahooapi.data.db.dao.YahooDao
import com.droidtechlab.yahooapi.data.network.ApiClient
import com.droidtechlab.yahooapi.data.network.Error
import com.droidtechlab.yahooapi.data.network.Failure
import com.droidtechlab.yahooapi.data.network.Success
import com.droidtechlab.yahooapi.data.repo.DataSourceRepo
import com.droidtechlab.yahooapi.ui.viewholders.AbstractViewHolder
import com.droidtechlab.yahooapi.ui.viewholders.CommentViewHolder
import com.droidtechlab.yahooapi.ui.viewholders.PlayerViewHolder
import com.droidtechlab.yahooapi.ui.viewmodels.Interactor
import com.droidtechlab.yahooapi.ui.viewmodels.InteractorVMFactory
import com.google.android.material.appbar.AppBarLayout

class PlayerListFragment : GenericRecyclerFragment() {

    private lateinit var yahooDao: YahooDao
    private lateinit var interactor: Interactor
    private var teamName: String = ""

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.apply {
            teamName = getString(TEAM_NAME) ?: ""
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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
        super.onViewCreated(view, savedInstanceState)
    }

    override fun loadData() {
        if (consumeCallback()) return
        val listOfComments = mutableListOf<AbstractViewHolder>()

        interactor.fetchTeamPlayersSummary(teamName)
            .observe(viewLifecycleOwner, Observer { result ->
                when (result) {
                    is Success -> {
                        result.data.forEach {
                            listOfComments.add(PlayerViewHolder(it))
                        }
                        updateList(listOfComments)
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

    companion object {

        private const val TEAM_NAME = "team_name"

        @JvmStatic
        fun getInstance(teamName: String) =
            PlayerListFragment().apply {
                arguments = Bundle().apply {
                    putString(TEAM_NAME, teamName)
                }
            }
    }
}