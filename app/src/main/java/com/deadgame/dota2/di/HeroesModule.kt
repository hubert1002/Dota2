package com.deadgame.dota2.di

import androidx.lifecycle.ViewModel
import com.deadgame.dota2.module.hero.HeroesFragment
import com.deadgame.dota2.module.hero.HeroesViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Created by liuwei04 on 2021/1/8.
 */
@Module
abstract class HeroesModule {
    @ContributesAndroidInjector(modules = [
        ViewModelBuilder::class
    ])
    internal abstract fun tasksFragment(): HeroesFragment

    @Binds
    @IntoMap
    @ViewModelKey(HeroesViewModel::class)
    abstract fun bindViewModel(viewmodel: HeroesViewModel): ViewModel
}