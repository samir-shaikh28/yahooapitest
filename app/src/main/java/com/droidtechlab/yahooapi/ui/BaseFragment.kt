package com.droidtechlab.yahooapi.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    protected val actionBar: ActionBar?
        get() = appCompatActivity?.supportActionBar

    private val appCompatActivity: AppCompatActivity?
        get() = activity as? AppCompatActivity?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Helper.setStatusBarNormal(activity,actionBar, requireContext())
        return super.onCreateView(inflater, container, savedInstanceState)
    }



    protected fun setSupportActionBar(toolbar: Toolbar) {
        appCompatActivity?.setSupportActionBar(toolbar)
    }

    protected fun requireAppContext(): Context {
        return requireContext().applicationContext
    }

    /**
     *  Check's if the fragment is detached or is being removed from it's parent
     *  Helps to consume code flow so that UI operations are not performed while fragment is removing or already detached.
     */
    fun consumeCallback(): Boolean {
        return isDetached || isRemoving || context == null
    }

}