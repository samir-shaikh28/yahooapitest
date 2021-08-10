package com.droidtechlab.yahooapi.ui.viewholders

import com.droidtechlab.yahooapi.R
import com.droidtechlab.yahooapi.data.db.entities.BatsmenInInningsEntity

class BatsmanInningsViewHolder(var  batsmenInInningsEntity: BatsmenInInningsEntity): AbstractViewHolder() {

    override fun getLayoutIdentifier(): Int {
        return R.layout.row_batsman_innings
    }
}