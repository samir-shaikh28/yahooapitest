package com.droidtechlab.yahooapi.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.droidtechlab.yahooapi.R
import com.droidtechlab.yahooapi.data.db.dao.YahooDao
import com.droidtechlab.yahooapi.data.db.entities.InningsEntity
import com.droidtechlab.yahooapi.databinding.FragmentInningsParentBinding
import com.droidtechlab.yahooapi.ui.adapter.ScoreCardViewPagerAdapter
import com.droidtechlab.yahooapi.ui.viewmodels.Interactor



class InningsParentFragment : BaseFragment() {

    private lateinit var yahooDao: YahooDao
    private lateinit var interactor: Interactor
    private lateinit var mBinding: FragmentInningsParentBinding
    private var inningsEntity: InningsEntity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.apply {
            inningsEntity = getParcelable(INNINGS_DATA)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_innings_parent, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolBar()
        loadData()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initToolBar() {
        mBinding.toolbar.apply {
            setSupportActionBar(this)
            actionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(true)
                title = "Innings"
                setHomeAsUpIndicator(R.drawable.ic_back_white)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            requireActivity().onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun loadData() {
        Log.d("###", "is innings entity null  ${inningsEntity  ==   null}")
        val adapter = ScoreCardViewPagerAdapter(childFragmentManager, inningsEntity!!)
        mBinding.viewpager.adapter = adapter
        mBinding.tabLayout.setupWithViewPager(mBinding.viewpager)
    }

    companion object {
        private const val INNINGS_DATA = "innings_data"
    }
}