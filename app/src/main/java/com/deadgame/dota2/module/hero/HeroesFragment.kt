package com.deadgame.dota2.module.hero

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.deadgame.dota2.R
import com.deadgame.dota2.base.BaseDaggerFragment
import com.deadgame.dota2.databinding.DotaHerosFragBinding
import com.deadgame.dota2.util.EventObserver
import com.deadgame.dota2.util.SGDecoration
import dagger.android.support.DaggerFragment
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by liuwei04 on 2021/1/8.
 */
class HeroesFragment  : BaseDaggerFragment(){


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<HeroesViewModel> { viewModelFactory }

    private lateinit var viewDataBinding: DotaHerosFragBinding
    private lateinit var listAdapter: HeroesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewDataBinding = DotaHerosFragBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        setHasOptionsMenu(true)
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Set the lifecycle owner to the lifecycle of the view
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        setupListAdapter()
        setupNavigation()

        viewModel.loadHeroes()

    }

    private fun setupListAdapter() {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            viewDataBinding.tasksList.layoutManager= StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            viewDataBinding.tasksList.addItemDecoration(SGDecoration(requireContext().resources.getDimensionPixelOffset(
                R.dimen.hero_dec)))
            listAdapter = HeroesAdapter(viewModel)
            viewDataBinding.tasksList.adapter = listAdapter
        } else {
            Timber.w("ViewModel not initialized when attempting to set up adapter.")
        }
    }

    private fun setupNavigation() {
        viewModel.openTaskEvent.observe(this.viewLifecycleOwner, EventObserver {
            Timber.i("onitemclick"+it)

            val intent = Intent()
            intent.action = "android.intent.action.VIEW"
            var aaa = "https://www.dota2.com.cn/hero/"+it.localized_name
            val content_url = Uri.parse(aaa)
            intent.data = content_url
            startActivity(intent)
        })
    }
}