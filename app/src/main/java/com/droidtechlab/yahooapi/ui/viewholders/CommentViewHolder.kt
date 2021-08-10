package com.droidtechlab.yahooapi.ui.viewholders

import com.droidtechlab.yahooapi.R

class CommentViewHolder(var comment: String): AbstractViewHolder() {

    override fun getLayoutIdentifier(): Int {
        return R.layout.row_commentary
    }

}