package com.droidtechlab.yahooapi.ui.viewholders

import com.droidtechlab.yahooapi.R
import com.droidtechlab.yahooapi.data.db.entities.PlayerEntity

class PlayerViewHolder(var playerEntity: PlayerEntity): AbstractViewHolder() {

    override fun getLayoutIdentifier(): Int {
        return R.layout.row_player
    }
}