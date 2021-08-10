package com.droidtechlab.yahooapi.ui

import android.content.Context
import android.os.Bundle
import com.droidtechlab.yahooapi.data.db.entities.BowlerInInningsEntity
import com.droidtechlab.yahooapi.data.db.entities.FallOfWicketEntity
import com.droidtechlab.yahooapi.ui.viewholders.AbstractViewHolder
import com.droidtechlab.yahooapi.ui.viewholders.BowlerInningsViewHolder
import com.droidtechlab.yahooapi.ui.viewholders.FallOfWicketViewHolder


class FallOfWicketListFragment : GenericRecyclerFragment() {

    private var listOfEntity: List<FallOfWicketEntity>? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.apply {
            listOfEntity = getParcelableArrayList(LIST_DATA)
        }
    }

    override fun loadData() {
        val listOfBowlerEntity = mutableListOf<AbstractViewHolder>()
        listOfEntity?.forEach {
            listOfBowlerEntity.add(FallOfWicketViewHolder(it))
        }
        updateList(listOfBowlerEntity)

    }

    companion object {
        const val LIST_DATA = "list_data"
        const val TYPE = "type"

        fun getInstance(listOfItem: ArrayList<FallOfWicketEntity>) =
            FallOfWicketListFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(LIST_DATA, listOfItem)
                }
            }
    }
}