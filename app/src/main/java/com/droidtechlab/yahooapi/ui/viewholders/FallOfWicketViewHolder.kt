package com.droidtechlab.yahooapi.ui.viewholders

import com.droidtechlab.yahooapi.R
import com.droidtechlab.yahooapi.data.db.entities.FallOfWicketEntity

class FallOfWicketViewHolder(var fallOfWicketEntity: FallOfWicketEntity) : AbstractViewHolder() {

    override fun getLayoutIdentifier(): Int {
        return R.layout.row_fall_of_wickets
    }
}