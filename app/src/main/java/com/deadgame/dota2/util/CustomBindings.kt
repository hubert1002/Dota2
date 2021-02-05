/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.deadgame.dota2.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.deadgame.dota2.R
import com.deadgame.dota2.data.*
import com.deadgame.dota2.module.hero.HeroesAdapter
import com.deadgame.dota2.module.history.HistoryAdapter
import com.deadgame.dota2.module.match.MatchPlayerAdapter
import com.deadgame.dota2.module.user.PlayersAdapter
import com.ethanhua.skeleton.SkeletonAdapter
import timber.log.Timber
import java.util.*

/**
 * [BindingAdapter]s for the [Task]s list.
 */
@BindingAdapter("app:heroes")
fun setHeroes(listView: RecyclerView, items: List<Hero>) {
    (listView.adapter as HeroesAdapter).submitList(items)
}

@BindingAdapter("app:history")
fun setHistory(listView: RecyclerView, items: List<MatchDetailInfo>) {
    (listView.adapter as HistoryAdapter).submitList(items)
}

@BindingAdapter("app:match")
fun setMatch(listView: RecyclerView, items: List<MatchInfo.PlayersDTO>) {
    if(listView.adapter is MatchPlayerAdapter)
    (listView.adapter as MatchPlayerAdapter).submitList(items)
}

@BindingAdapter("app:players")
fun setPlayers(listView: RecyclerView, items: List<Player>) {
    (listView.adapter as PlayersAdapter).submitList(items)
}

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl).placeholder(R.drawable.default_avater)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}

@BindingAdapter("imageFromHeroId")
fun bindImageFromHeroId(view: ImageView, id:Int) {
    var id= CommonData.getHeroIconUrl(id)
    Glide.with(view.context)
            .load(id).placeholder(R.drawable.default_avater)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
}

@BindingAdapter("imageFromItemId")
fun bindImageFromItemId(view: ImageView, id:Int) {
    var id= CommonData.getItemIconUrl(id)
    Glide.with(view.context)
        .load(id).placeholder(R.drawable.default_avater)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(view)
}

@BindingAdapter("imageFromUrlWithRandomSize")
fun imageFromUrlWithRandomSize(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        val max = 670
        val min = 300
        val random = Random()
        val width: Int = random.nextInt(max) % (max - min + 1) + min
        val height = (width * 1.16).toInt()
        Timber.i("bindImageFromUrl$width $height")
        Glide.with(view.context)
            .load(imageUrl).placeholder(R.drawable.default_avater).override(width,height)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}

@BindingAdapter("heroName")
fun setHeroName(view: TextView, id: Int) {
    view.text = CommonData.getHeroName(id)
}

@BindingAdapter("isWin")
fun isWin(view: TextView, isWin: Boolean) {
    if(isWin){
        view.text = "胜利"
        view.setTextColor(view.resources.getColor(R.color.material_green))
    }else{
        view.text = "失败"
        view.setTextColor(view.getResources().getColor(R.color.material_red))
    }
}