package com.droidtechlab.yahooapi.ui

import android.os.Bundle
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
import com.droidtechlab.yahooapi.databinding.FragmentMatchInfoBinding
import com.droidtechlab.yahooapi.ui.viewmodels.Interactor
import com.droidtechlab.yahooapi.ui.viewmodels.InteractorVMFactory
import com.droidtechlab.yahooapi.ui.viewmodels.MatchInfoViewModel


class MatchInfoFragment : BaseFragment() {

    private lateinit var matchInfoViewModel: MatchInfoViewModel
    private lateinit var interactor: Interactor
    private lateinit var yahooDao: YahooDao
    private lateinit var mBinding: FragmentMatchInfoBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_match_info, container, false)
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
        matchInfoViewModel = ViewModelProvider(this).get(MatchInfoViewModel::class.java)


        mBinding.apply {
            lifecycleOwner = viewLifecycleOwner
            group = matchInfoViewModel
        }
        initToolBar()
        loadData()
    }

    private fun initToolBar() {
        mBinding.toolbar.apply {
            setSupportActionBar(this)
            actionBar?.title = "Match Info"
        }
    }

    private fun loadData() {
        if (consumeCallback()) return

        interactor.fetchMatchInfo().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Success -> {
                    matchInfoViewModel.updateUI(result.data)
                }
                is Failure -> {
                }
                is Error -> {
                }

            }
        })
    }
}