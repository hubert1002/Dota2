package com.deadgame.dota2.module.match

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.deadgame.dota2.R
import com.deadgame.dota2.base.BaseDaggerFragment
import com.deadgame.dota2.databinding.DotaHerosFragBinding
import com.deadgame.dota2.databinding.DotaHistoryFragBinding
import com.deadgame.dota2.databinding.DotaMatchFragBinding
import com.deadgame.dota2.module.user.PlayersAdapter
import com.deadgame.dota2.module.user.UserFragmentArgs
import com.deadgame.dota2.util.EventObserver
import com.deadgame.dota2.util.SGDecoration
import com.ethanhua.skeleton.Skeleton
import dagger.android.support.DaggerFragment
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by liuwei04 on 2021/1/8.
 */
class MatchFragment : BaseDaggerFragment(){
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<MatchViewModel> { viewModelFactory }

    private lateinit var listAdapter: MatchPlayerAdapter
//    private var initId:String?= null
    private lateinit var viewDataBinding: DotaMatchFragBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DotaMatchFragBinding.inflate(inflater, container, false).apply {
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

        requireArguments().apply {
            var id = UserFragmentArgs.fromBundle(requireArguments()).id
            Timber.i("id$id")
            viewModel.getMatchInfo(id)
        }

    }

    private fun setupListAdapter() {
//        val viewModel = viewDataBinding.viewmodel
//        if (viewModel != null) {
//            listAdapter = MatchPlayerAdapter(viewModel)
//            viewDataBinding.tasksList.adapter = listAdapter
//        } else {
//            Timber.w("ViewModel not initialized when attempting to set up adapter.")
//        }
    }

    private fun setupNavigation() {
        viewModel.openTaskEvent.observe(this.viewLifecycleOwner, EventObserver {
            Timber.i("onitemclick"+it)

        })
    }
    private fun setupLoading() {
        val viewModel = viewDataBinding.viewmodel
        listAdapter = MatchPlayerAdapter(viewModel!!)
        viewDataBinding.tasksList.layoutManager= LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)

        var a =Skeleton.bind(viewDataBinding.tasksList).adapter(listAdapter).load(R.layout.skeleton_item_new).show()
        viewModel.dataLoading.observe(this.viewLifecycleOwner, Observer {
            Timber.i("setupLoading"+it)
            if(!it){
                a.hide()
            }
        })
    }
}