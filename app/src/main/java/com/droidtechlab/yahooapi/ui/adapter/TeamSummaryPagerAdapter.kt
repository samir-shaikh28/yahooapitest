package com.droidtechlab.yahooapi.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.droidtechlab.yahooapi.ui.PlayerListFragment

class TeamSummaryPagerAdapter(fragmentManager: FragmentManager, var list: List<String>) :
    FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {


    override fun getItem(position: Int): Fragment {
        return PlayerListFragment.getInstance(list[position])
    }

    override fun getCount(): Int {
        return list.size ?: 0
    }

    override fun getPageTitle(position: Int): CharSequence {
        var title = ""
        list.let { tabs ->
            tabs[position].let { name ->
                title = name
            }
        }
        return title
    }
}