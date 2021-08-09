package com.droidtechlab.yahooapi.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.droidtechlab.yahooapi.R
import com.droidtechlab.yahooapi.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var mBinding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navHostFragment =
            childFragmentManager.findFragmentById(R.id.frag_container) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(mBinding.bottomNavView, navHostFragment.navController)
    }


    companion object {

        @JvmStatic
        fun newInstance() = MainFragment()
    }
}