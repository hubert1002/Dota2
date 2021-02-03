package com.deadgame.dota2.module.home

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.deadgame.dota2.module.hero.HeroesFragment
import com.deadgame.dota2.module.history.HistoryFragment
import com.deadgame.dota2.module.user.UserFragment
import java.util.*

/**
 * Created by liuwei04 on 2021/1/20.
 */

class HomeFragmentAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    private val fragmentList: MutableList<Fragment>
    val titleList: MutableList<String>


    fun addFragment(fragment: Fragment, title: String) {
        fragmentList.add(fragment)
        titleList.add(title)
    }

    fun getTitle(position: Int): String {
        return when (position) {
            0 -> "User"
            1 -> "History"
            else -> "hero"
        }
    }

    init {
        fragmentList = ArrayList<Fragment>()
        titleList = ArrayList()
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                UserFragment()
            }
            1 -> {
                HistoryFragment()
            }
            else -> {
                HeroesFragment()
            }
        }
    }
}