package com.deadgame.dota2.base

import android.os.Bundle
import com.deadgame.dota2.util.onActivityCreated
import dagger.android.support.DaggerFragment
import timber.log.Timber

/**
 * Created by liuwei04 on 2021/1/20.
 */

open class BaseDaggerFragment : DaggerFragment() {
    override fun onActivityCreated(savedInstanceState: Bundle?){
        super.onActivityCreated(savedInstanceState)
        Timber.i("onActivityCreated"+this.javaClass)
    }

    override fun onDestroyView(){
        super.onDestroyView()
        Timber.i("onDestroyView"+this.javaClass)
    }


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        Timber.i("onCreate"+this.javaClass)
    }

    override fun onDestroy(){
        super.onDestroy()
        Timber.i("onDestroy"+this.javaClass)
    }
}