package com.deadgame.dota2.data.source

import com.deadgame.dota2.data.*

/**
 * Created by liuwei04 on 2021/1/8.
 */
interface DotaDataSource {
    suspend fun getHeroes(): Result<List<Hero>>
    suspend fun getHeroInfo(id:List<Int>): Result<List<Hero>>
    suspend fun getItems(): Result<List<Item>>
    suspend fun saveHeroes(heroes: List<Hero>)
    suspend fun getPlayersInfo(ids:List<String>):Result<List<Player>>
    suspend fun getFriendsList(id: String): Result<List<Player>>
    suspend fun getMatchesHistory(id: String,num:String): Result<List<MatchBriefInfo>>
    suspend fun getMatchDetails(id: String): Result<MatchDetailInfo>
//    suspend fun getMatchesHistoryForShow(id: String,num:String): Result<List<MatchDetailInfo>>
}