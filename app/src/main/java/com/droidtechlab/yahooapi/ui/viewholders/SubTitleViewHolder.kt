package com.droidtechlab.yahooapi.ui.viewholders

import com.droidtechlab.yahooapi.R

class SubTitleViewHolder(var title: String): AbstractViewHolder() {

    override fun getLayoutIdentifier(): Int {
        return R.layout.row_subtitle
    }
}