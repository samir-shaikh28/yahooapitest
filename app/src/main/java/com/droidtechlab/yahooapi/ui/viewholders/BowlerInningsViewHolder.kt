package com.droidtechlab.yahooapi.ui.viewholders

import com.droidtechlab.yahooapi.R
import com.droidtechlab.yahooapi.data.db.entities.BowlerInInningsEntity

class BowlerInningsViewHolder(var bowlerInInningsEntity: BowlerInInningsEntity): AbstractViewHolder() {

    override fun getLayoutIdentifier(): Int {
        return R.layout.row_bowler_innings
    }
}