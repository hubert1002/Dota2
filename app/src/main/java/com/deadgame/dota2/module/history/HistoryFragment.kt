package com.deadgame.dota2.module.history

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.deadgame.dota2.R
import com.deadgame.dota2.base.BaseDaggerFragment
import com.deadgame.dota2.databinding.DotaHerosFragBinding
import com.deadgame.dota2.databinding.DotaHistoryFragBinding
import com.deadgame.dota2.module.home.HomeFragmentDirections
import com.deadgame.dota2.module.user.UserFragmentDirections
import com.deadgame.dota2.util.EventObserver
import com.deadgame.dota2.util.SGDecoration
import com.ethanhua.skeleton.Skeleton
import com.ethanhua.skeleton.ViewSkeletonScreen
import dagger.android.support.DaggerFragment
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by liuwei04 on 2021/1/8.
 */
class HistoryFragment  : BaseDaggerFragment(){
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<HistoryViewModel> { viewModelFactory }

    private var initId:String?= null
    private lateinit var viewDataBinding: DotaHistoryFragBinding
    private lateinit var listAdapter: HistoryAdapter
    private var viewSkeletonScreen : ViewSkeletonScreen? =null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DotaHistoryFragBinding.inflate(inflater, container, false).apply {
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
        setupLoading()
        if(viewModel.items.value.isNullOrEmpty())
        viewModel.getHistoryList(initId)

    }

    private fun setupListAdapter() {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
//            viewDataBinding.tasksList.layoutManager= StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
//            viewDataBinding.tasksList.addItemDecoration(SGDecoration(requireContext().resources.getDimensionPixelOffset(
//                R.dimen.hero_dec)))
            listAdapter = HistoryAdapter(viewModel)
            viewDataBinding.tasksList.adapter = listAdapter
        } else {
            Timber.w("ViewModel not initialized when attempting to set up adapter.")
        }
    }

    private fun setupNavigation() {
        viewModel.openTaskEvent.observe(this.viewLifecycleOwner, EventObserver {
            Timber.i("onitemclick"+it)

            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToMatchFragment(it.match_id.toString()))


        })
    }
    private fun  setupLoading(){
        viewSkeletonScreen= Skeleton.bind(viewDataBinding.tasksContainer)
            .load(R.layout.skeleton_image_new)
            .color(R.color.shimmer_color_for_image)
            .angle(20)
            .show()

        viewModel.dataLoading.observe(this.viewLifecycleOwner, Observer {
            Timber.i("setupLoading"+it)
            if(!it){
                viewSkeletonScreen!!.hide()
            }else{
                viewSkeletonScreen!!.show()
            }
        })
    }

}