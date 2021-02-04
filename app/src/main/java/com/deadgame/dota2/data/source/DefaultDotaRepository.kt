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
package com.deadgame.dota2.data.source

import com.deadgame.dota2.data.*
import com.deadgame.dota2.data.source.remote.NetErrorException
import com.deadgame.dota2.data.source.remote.OpenDotaWebApi
import com.deadgame.dota2.data.source.remote.WebApi
import com.deadgame.dota2.di.ApplicationModule
import com.deadgame.dota2.util.Util
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.lang.Exception
import java.util.ArrayList
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap
import javax.inject.Inject

/**
 * Concrete implementation to load tasks from the data sources into a cache.
 *
 * To simplify the sample, this repository only uses the local data source only if the remote
 * data source fails. Remote is the source of truth.
 */
class DefaultDotaRepository @Inject constructor(
    @ApplicationModule.DotaRemoteDataSource private val remoteDS: DotaDataSource,
    @ApplicationModule.DotaLocalDataSource private val localDS: DotaDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : DotaRepository {
    override suspend fun getHeroes(): Result<List<Hero>> = withContext(ioDispatcher){
        return@withContext fetchHeroes()
    }

    private suspend fun fetchHeroes(): Result<List<Hero>> {
        // Remote first
        val remote = remoteDS.getHeroes()
//        when (remote) {
//            is Error -> Timber.w("Remote data source fetch failed")
//            is Result.Success -> {
////                refreshLocalDataSource(remoteTask.data)
//                return remote
//            }
//            else -> throw IllegalStateException()
//        }
        val local = localDS.getHeroes()
        return local
    }

    override suspend fun getItems(): Result<List<Item>> {
        val remote = remoteDS.getItems()
//        val local = localDS.getItems()
        return remote
    }

    override suspend fun saveHeroes(items: List<Hero>) = withContext(ioDispatcher){
        localDS.saveHeroes(items)
    }

    override suspend fun getPlayersInfo(ids: List<String>) = withContext(ioDispatcher){
        return@withContext loadUserDateRemote(ids)
    }

    override suspend fun getFriendsList(id: String) = withContext(ioDispatcher){
        return@withContext loadUserFriendInfo(id)
    }

    override fun getCurrentPlayerId():String? {
        return Util.get64Id(150644870)
    }

    suspend fun loadUserDateRemote(id: List<String>): Result<List<Player>> {
       return remoteDS.getPlayersInfo(id)
    }

    suspend fun loadUserFriendInfo(id: String):  Result<List<Player>>{
        var friendInfo =  remoteDS.getFriendsList(id)
        return friendInfo;
//        if(friendInfo is Result.Success){
//            var ids = ArrayList<String>()
//            for ((index,e) in friendInfo.data.withIndex()){
//                ids.add(e.steamid!!)
//            }
//            var friends = remoteDS.getPlayersInfo(ids)
//
//        }
//        return Result.Error(NetErrorException(friendInfo))
    }

    override suspend fun getMatchesHistory(id: String,num:String) = withContext(ioDispatcher){
        return@withContext getMatchesHistoryImp(id,num)
    }
    suspend fun getMatchesHistoryImp(id: String,num:String): Result<List<MatchBriefInfo>>{
        var friendInfo =  remoteDS.getMatchesHistory(id,num)
        return friendInfo
    }

    override suspend fun getMatchDetails(id: String) = withContext(ioDispatcher){
        return@withContext getMatchesHistoryImp(id)
    }

    suspend fun getMatchesHistoryImp(id: String): Result<MatchDetailInfo>{
        var info =  remoteDS.getMatchDetails(id)
        return info
    }

    //合成
    override suspend fun getMatchesHistoryForShow(id: String,num:String) = withContext(ioDispatcher){
        return@withContext getMatchesHistoryForShowImp(id,num)
    }

    private suspend fun getMatchesHistoryForShowImp(id: String,num:String):  Result<List<MatchDetailInfo>>{
        var netResult= remoteDS.getMatchesHistory(id,num)
        Timber.i("getMatchesHistoryForShowImp"+netResult)
        if(netResult is Result.Success){
            var result = ArrayList<MatchDetailInfo>()
            for (item in netResult.data){
                var matchinfo = remoteDS.getMatchDetails(item.match_id!!)
                if (matchinfo is Result.Success){
                    var _player :PlayerDetailInfo?=null
                    for (player in matchinfo.data.players){
                        if(Util.get64Id(player.account_id) == id){
                            _player = player
                            var ids = ArrayList<Int>()
                            ids.add(player.hero_id)
                            var heroes = localDS.getHeroInfo(ids)
                            if(heroes is Result.Success){
                                _player.heroInfo = heroes.data[0]
                            }
                            break
                        }
                    }
                    matchinfo.data.currentPlayer = _player

                    result.add(matchinfo.data)
                }
            }
            if (result.size>0){
                return Result.Success(result)
            }else{
                return Result.Error(NetErrorException("getMatchesHistoryForShow no data"))
            }
        }else{
            return Result.Error(NetErrorException("getMatchesHistoryForShow no data"))
        }

    }


    override suspend fun getMatchInfo(id: String) = withContext(ioDispatcher){
        return@withContext getMatchInfoImp(id)
    }

    suspend fun getMatchInfoImp(id: String): Result<MatchInfo>{
        var info =  OpenDotaWebApi.getMatchInfo(id)
        if(info!=null){
            return Result.Success(info)
        }else{
            return Result.Error(NetErrorException("getMatchesHistoryForShow no data"))
        }
    }



}
