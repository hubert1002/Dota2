package com.deadgame.dota2.module.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.NavHostController
import com.deadgame.dota2.databinding.DotaHerosFragBinding
import com.deadgame.dota2.databinding.ItemUserFragmentBinding
import com.deadgame.dota2.module.hero.HeroesAdapter
import com.deadgame.dota2.module.hero.HeroesViewModel
import com.deadgame.dota2.util.EventObserver
import dagger.android.support.DaggerFragment
import timber.log.Timber
import javax.inject.Inject

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.deadgame.dota2.R
import com.deadgame.dota2.base.BaseDaggerFragment
import com.deadgame.dota2.module.home.HomeFragmentDirections
import com.ethanhua.skeleton.Skeleton
import com.ethanhua.skeleton.SkeletonScreen
import com.ethanhua.skeleton.ViewSkeletonScreen

//import com.deadgame.dota2.module.home.HomeFragmentDirections

/**
 * Created by liuwei04 on 2021/1/22.
 */
class UserFragment : BaseDaggerFragment(){
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<UserViewModel> { viewModelFactory }

    private lateinit var viewDataBinding: ItemUserFragmentBinding

    private lateinit var listAdapter: PlayersAdapter

    private var initId:String?= null

    private var viewSkeletonScreen : ViewSkeletonScreen? =null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        viewDataBinding = ItemUserFragmentBinding.inflate(inflater, container, false).apply {
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
        arguments?.apply {
            initId = UserFragmentArgs.fromBundle(requireArguments()).id
        }
        Timber.i("id$id")
        viewModel.loadUser(initId)

    }

    private fun setupListAdapter() {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            listAdapter = PlayersAdapter(viewModel)
            viewDataBinding.recyclerFriendsList.adapter = listAdapter
        } else {
            Timber.w("ViewModel not initialized when attempting to set up adapter.")
        }
    }

    private fun setupNavigation() {
        viewModel.openTaskEvent.observe(this.viewLifecycleOwner, EventObserver {
            Timber.i("onitemclick"+it)
//            UserFragmentDirections.actionUserFragmentToHomeFragment()
//            val action = TasksFragmentDirections.actionTasksFragmentToTaskDetailFragment(taskId)

            if(initId == null){
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToUserFragment(it))
            }else{
                findNavController().navigate( UserFragmentDirections.actionUserFragmentToUserFragment(it))
            }

        })
    }
    private fun  setupLoading(){
        viewSkeletonScreen = Skeleton.bind(viewDataBinding.rootview).load(R.layout.skeleton_image_new).shimmer(false).show()
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