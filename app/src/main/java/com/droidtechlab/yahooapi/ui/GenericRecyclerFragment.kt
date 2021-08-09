package com.droidtechlab.yahooapi.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.droidtechlab.yahooapi.R
import com.droidtechlab.yahooapi.databinding.GenericRecyclerFragmentBinding
import com.droidtechlab.yahooapi.utils.VerticalSpaceItemDecoration

abstract class GenericRecyclerFragment : Fragment() {

    lateinit var mBinding: GenericRecyclerFragmentBinding

    protected val actionBar: ActionBar?
        get() = appCompatActivity?.supportActionBar

    private val appCompatActivity: AppCompatActivity?
        get() = activity as? AppCompatActivity?


    protected fun setSupportActionBar(toolbar: Toolbar) {
        appCompatActivity?.setSupportActionBar(toolbar)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.generic_recycler_fragment, container, false)
        mBinding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        mBinding.recyclerView.addItemDecoration(VerticalSpaceItemDecoration(resources.getDimension(R.dimen.vertical_margin_half).toInt()))
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }

    abstract fun loadData()

    /**
     *  Check's if the fragment is detached or is being removed from it's parent
     *  Helps to consume code flow so that UI operations are not performed while fragment is removing or already detached.
     */
    fun consumeCallback(): Boolean {
        return isDetached || isRemoving || context == null
    }

}