package com.deadgame.dota2.di

import androidx.lifecycle.ViewModel
import com.deadgame.dota2.module.user.UserFragment
import com.deadgame.dota2.module.user.UserViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Created by liuwei04 on 2021/1/8.
 */
@Module
abstract class UserModule {
    @ContributesAndroidInjector(modules = [
        ViewModelBuilder::class
    ])
    internal abstract fun userFragment(): UserFragment

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    abstract fun bindViewModel(viewmodel: UserViewModel): ViewModel
}