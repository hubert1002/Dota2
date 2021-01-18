package com.deadgame.dota2.data.source

import com.deadgame.dota2.data.Hero
import com.deadgame.dota2.data.Item
import com.deadgame.dota2.data.Result
/**
 * Created by liuwei04 on 2021/1/8.
 */
interface DotaDataSource {
    suspend fun getHeroes(): Result<List<Hero>>
    suspend fun getItems(): Result<List<Item>>
    suspend fun saveHeroes(heroes: List<Hero>)
}