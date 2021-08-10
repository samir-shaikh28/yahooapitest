package com.droidtechlab.yahooapi.ui.viewholders

import android.view.View
import com.droidtechlab.yahooapi.R
import com.droidtechlab.yahooapi.data.db.entities.InningsEntity
import com.droidtechlab.yahooapi.data.db.entities.PlayerEntity
import com.droidtechlab.yahooapi.ui.NavigationListener

class InningViewHolder(var inningsEntity: InningsEntity, var listener: NavigationListener): AbstractViewHolder() {

    override fun getLayoutIdentifier(): Int {
        return R.layout.row_innings
    }

    fun openInningsPagerScreen(v: View) {
        listener.openInningsPagerScreen(inningsEntity)
    }
}