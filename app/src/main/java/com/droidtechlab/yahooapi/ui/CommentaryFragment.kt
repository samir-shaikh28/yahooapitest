package com.droidtechlab.yahooapi.ui

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
import com.droidtechlab.yahooapi.ui.viewmodels.Interactor
import com.droidtechlab.yahooapi.ui.viewmodels.InteractorVMFactory
import com.google.android.material.appbar.AppBarLayout

class CommentaryFragment : GenericRecyclerFragment() {

    private lateinit var yahooDao: YahooDao
    private lateinit var interactor: Interactor
    private var toolbar: Toolbar? = null
    private var appBar: AppBarLayout? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setStubView(R.layout.lyt_toolbar)

        toolbar = stubView?.findViewById(R.id.toolbar)
        appBar = stubView?.findViewById(R.id.appbar)

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
            actionBar?.title = "Commentary"
        }
    }


    override fun loadData() {
        if (consumeCallback()) return
        val listOfComments = mutableListOf<AbstractViewHolder>()

        interactor.fetchCommentary().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Success -> {
                    Log.d("###", "fetch comment success")
                    result.data.forEach {
                        Log.d("###", "comment:  ${it.comment}")
                        listOfComments.add(CommentViewHolder(it.comment))
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
}