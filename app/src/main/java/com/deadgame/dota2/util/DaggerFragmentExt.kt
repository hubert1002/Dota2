package com.deadgame.dota2.util
import android.os.Bundle
import dagger.android.support.DaggerFragment
import timber.log.Timber

/**
 * Created by liuwei04 on 2021/2/5.
 */

fun DaggerFragment.onActivityCreated(savedInstanceState: Bundle?){
    onActivityCreated(savedInstanceState)
    Timber.i("onActivityCreated"+this.javaClass)
}

fun DaggerFragment.onDestroyView(){
    onDestroyView()
    Timber.i("onDestroyView"+this.javaClass)
}


fun DaggerFragment.onCreate(savedInstanceState: Bundle?){
    onCreate(savedInstanceState)
    Timber.i("onCreate"+this.javaClass)
}

fun DaggerFragment.onDestroy(){
    onDestroy()
    Timber.i("onDestroy"+this.javaClass)
}