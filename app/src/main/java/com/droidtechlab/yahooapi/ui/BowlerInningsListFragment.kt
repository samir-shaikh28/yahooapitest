package com.droidtechlab.yahooapi.ui

import android.content.Context
import android.os.Bundle
import com.droidtechlab.yahooapi.data.db.entities.BatsmenInInningsEntity
import com.droidtechlab.yahooapi.data.db.entities.BowlerInInningsEntity
import com.droidtechlab.yahooapi.ui.viewholders.AbstractViewHolder
import com.droidtechlab.yahooapi.ui.viewholders.BowlerInningsViewHolder

class BowlerInningsListFragment : GenericRecyclerFragment() {

    private var listOfEntity: List<BowlerInInningsEntity>? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.apply {
            listOfEntity = getParcelableArrayList(LIST_DATA)
        }
    }

    override fun loadData() {
        val listOfBowlerEntity = mutableListOf<AbstractViewHolder>()
        listOfEntity?.forEach {
            listOfBowlerEntity.add(BowlerInningsViewHolder(it))
        }
        updateList(listOfBowlerEntity)

    }

    companion object {
        const val LIST_DATA = "list_data"
        const val TYPE = "type"

        fun getInstance(listOfItem: ArrayList<BowlerInInningsEntity>) =
            BowlerInningsListFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(LIST_DATA, listOfItem)
                }
            }
    }
}