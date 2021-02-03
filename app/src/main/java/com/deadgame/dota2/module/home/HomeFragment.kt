package com.deadgame.dota2.module.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.deadgame.dota2.R
import com.deadgame.dota2.databinding.DotaHerosFragBinding
import com.deadgame.dota2.databinding.DotaHomeFragBinding
import com.deadgame.dota2.module.hero.HeroesAdapter
import com.deadgame.dota2.module.hero.HeroesViewModel
import com.deadgame.dota2.util.EventObserver
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import dagger.android.support.DaggerFragment
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by liuwei04 on 2021/1/20.
 */
class HomeFragment: Fragment() {
//    @Inject
//    lateinit var viewModelFactory: ViewModelProvider.Factory

//    private val viewModel by viewModels<HeroesViewModel> { viewModelFactory }

    private lateinit var viewDataBinding: DotaHomeFragBinding
    private lateinit var listAdapter: HeroesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DotaHomeFragBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Set the lifecycle owner to the lifecycle of the view
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner


        setupListAdapter()

    }

    private fun setupListAdapter() {
        viewDataBinding.viewpager.adapter = HomeFragmentAdapter(this)
        viewDataBinding.run {
            tabLayout.addTab(tabLayout.newTab().setText("tab0").setIcon(R.drawable.ic_launcher_background))
            tabLayout.addTab(tabLayout.newTab().setText("tab1"))
            tabLayout.addTab(tabLayout.newTab().setText("tab2"))

            tabLayout.addOnTabSelectedListener(object :OnTabSelectedListener{
                override fun onTabReselected(tab: TabLayout.Tab?) {
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabSelected(tab: TabLayout.Tab?) {
                    viewpager.currentItem = tabLayout.selectedTabPosition
                }

            })
            viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    tabLayout.setScrollPosition(position,0f,false)
                }
            })

        }
    }

}

