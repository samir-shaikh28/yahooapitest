package com.droidtechlab.yahooapi.ui.listitem

import com.droidtechlab.yahooapi.R

class SubTitleListItem(var title: String): AbstractListItem() {

    override fun getLayoutIdentifier(): Int {
        return R.layout.row_subtitle
    }
}