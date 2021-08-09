package com.droidtechlab.yahooapi.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.droidtechlab.yahooapi.R
import com.droidtechlab.yahooapi.data.db.DBInstance
import com.droidtechlab.yahooapi.data.db.dao.YahooDao
import com.droidtechlab.yahooapi.data.network.ApiClient
import com.droidtechlab.yahooapi.data.network.Error
import com.droidtechlab.yahooapi.data.network.Failure
import com.droidtechlab.yahooapi.data.network.Success
import com.droidtechlab.yahooapi.data.repo.DataSourceRepo
import com.droidtechlab.yahooapi.databinding.FragmentSplashScreenBinding
import com.droidtechlab.yahooapi.ui.viewmodels.Interactor
import com.droidtechlab.yahooapi.ui.viewmodels.InteractorVMFactory


class SplashScreenFragment : Fragment() {


    private lateinit var mBinding: FragmentSplashScreenBinding
    private lateinit var interactor: Interactor
    private lateinit var yahooDao: YahooDao
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        Log.d("###", "splash   screen")
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_splash_screen, container, false)

        yahooDao = DBInstance.getDbInstance(requireContext()).yahooDao()
        interactor = ViewModelProvider(
            this,
            InteractorVMFactory(DataSourceRepo(requireActivity(), ApiClient.apiClient!!, yahooDao))
        ).get(Interactor::class.java)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        loadData()
    }

    private fun loadData() {
        interactor.fetchApiResponse().observe(viewLifecycleOwner, Observer { results ->
            when (results) {
                is Success -> {
                    navController.navigate(R.id.action_splashScreenFragment_to_mainFragment)
                }
                is Error -> {
                    Log.d("###", "error")
                    // Can show a view for Error
                }
                is Failure -> {
                    // Can show a view for Failure
                    Log.d("###", "Failure - ${results.throwable}")
                }
            }
        })
    }

    companion object {

        @JvmStatic
        fun newInstance() = SplashScreenFragment()
    }
}