package com.droidtechlab.yahooapi.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import com.droidtechlab.yahooapi.data.db.entities.BatsmenInInningsEntity
import com.droidtechlab.yahooapi.ui.viewholders.AbstractViewHolder
import com.droidtechlab.yahooapi.ui.viewholders.BatsmanInningsViewHolder

class BatsmanInningsListFragment : GenericRecyclerFragment() {

    private var listOfEntity: List<BatsmenInInningsEntity>? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.apply {
            listOfEntity = getParcelableArrayList(LIST_DATA)
        }
    }

    override fun loadData() {
        Log.d("###", "batsmen inning loadData")
        val listOfBatsmenEntity = mutableListOf<AbstractViewHolder>()
        listOfEntity?.forEach {
            Log.d("###", "batsman: ${it.batsman}")
            listOfBatsmenEntity.add(BatsmanInningsViewHolder(it))
        }
        updateList(listOfBatsmenEntity)

    }

    companion object {
        const val LIST_DATA = "list_data"
        const val TYPE = "type"

        fun getInstance(listOfItem: ArrayList<BatsmenInInningsEntity>) =
            BatsmanInningsListFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(LIST_DATA, listOfItem)
                }
            }
    }
}