package com.droidtechlab.yahooapi.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
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
import com.droidtechlab.yahooapi.databinding.FragmentSummaryBinding
import com.droidtechlab.yahooapi.ui.adapter.TeamSummaryPagerAdapter
import com.droidtechlab.yahooapi.ui.viewmodels.Interactor
import com.droidtechlab.yahooapi.ui.viewmodels.InteractorVMFactory


class SummaryFragment : BaseFragment() {

    private lateinit var yahooDao: YahooDao
    private lateinit var interactor: Interactor
    private lateinit var mBinding: FragmentSummaryBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_summary, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        loadData()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initToolBar() {
        mBinding.toolbar.apply {
            setSupportActionBar(this)
            actionBar?.title = "Teams"
        }
    }

    private fun loadData() {
        if (consumeCallback()) return

        interactor.fetchTeamName().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Success -> {
                    val adapter = TeamSummaryPagerAdapter(childFragmentManager, result.data)
                    mBinding.viewpager.adapter =  adapter
                    mBinding.tabLayout.setupWithViewPager(mBinding.viewpager)
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