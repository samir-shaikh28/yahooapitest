package com.droidtechlab.yahooapi.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.droidtechlab.yahooapi.data.db.entities.FallOfWicketEntity
import com.droidtechlab.yahooapi.data.db.entities.InningsEntity
import com.droidtechlab.yahooapi.ui.BatsmanInningsListFragment
import com.droidtechlab.yahooapi.ui.BowlerInningsListFragment
import com.droidtechlab.yahooapi.ui.FallOfWicketListFragment

class ScoreCardViewPagerAdapter(
    fragmentManager: FragmentManager,
    var inningsEntity: InningsEntity
) :
    FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var tabs = arrayOf("Batsman", "Bowler", "Fall Of Wickets")

    override fun getItem(position: Int): Fragment {

        return when (position) {
            0 -> {
                BatsmanInningsListFragment.getInstance(inningsEntity.batsmanList!!)
            }
            1 -> {
                BowlerInningsListFragment.getInstance(inningsEntity.bowlerList!!)
            }
            else -> {
//                BowlerInningsListFragment.getInstance(inningsEntity.bowlerList!!)

                FallOfWicketListFragment.getInstance(inningsEntity.fallOfWicketList!!)
            }
        }
    }

    override fun getCount(): Int {
        return tabs.size ?: 0
    }

    override fun getPageTitle(position: Int): CharSequence {
        var title = ""
        tabs.let { tabs ->
            tabs[position].let { name ->
                title = name
            }
        }
        return title
    }
}