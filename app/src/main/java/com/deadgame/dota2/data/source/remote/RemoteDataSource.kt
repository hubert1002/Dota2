package com.deadgame.dota2.data.source.remote

import com.deadgame.dota2.data.*
import com.deadgame.dota2.data.source.DotaDataSource
import com.deadgame.dota2.util.Util
import timber.log.Timber
import java.lang.Exception
import java.util.*
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * Created by liuwei04 on 2021/1/8.
 */
object RemoteDataSource : DotaDataSource {
    private var TASKS_SERVICE_DATA = LinkedList<Hero>()

    override suspend fun getHeroes(): Result<List<Hero>> {
        return Result.Success(TASKS_SERVICE_DATA)
    }

    override suspend fun getHeroInfo(id: List<Int>): Result<List<Hero>> {
        TODO("Not yet implemented")
    }

    override suspend fun getItems(): Result<List<Item>> {
        var test= WebApi.getItems(object : Observer<NetResult<Item>?> {
            override fun onError(e: Throwable) {

            }

            override fun onNext(t: NetResult<Item>) {
                Timber.i("onNext$t")
            }

            override fun onComplete() {
                Timber.i("onComplete")
            }

            override fun onSubscribe(d: Disposable) {
                Timber.i("onSubscribe")
            }
        })
        var netResult= WebApi.getItemsSync()
        if (netResult!=null&& netResult.result?.status ==200){
            return Result.Success(netResult.result!!.items!!)
        }else{
            return Result.Error(Exception("test"))
        }
    }

    override suspend fun saveHeroes(heroes: List<Hero>) {
        TODO("Not yet implemented")
    }

    override suspend fun getPlayersInfo(ids: List<String>): Result<List<Player>> {
        var netResult= WebApi.getUsers(ids)
        Timber.i("loadUserDate"+netResult)
        if (netResult?.response?.players!=null){
            return Result.Success(netResult.response!!.players!!)
        }else{
            return Result.Error(NetErrorException("loadUserDate"))
        }
    }

    override suspend fun getFriendsList(id: String): Result<List<Player>> {
        var friendInfo= WebApi.getFriends(id)

        if(friendInfo?.friendslist?.friends!=null){
            var ids = ArrayList<String>()
            var lists = friendInfo!!.friendslist!!.friends!!
            for (item in lists){
                ids.add(item.steamid!!)
            }
            var friends = WebApi.getUsers(ids)

            if (friends?.response?.players!=null){
                return Result.Success(friends.response!!.players!!)
            }else{
                return Result.Error(NetErrorException("loadUserDate"))
            }
        }else{
            return Result.Error(NetErrorException("loadUserDate"))
        }
    }


    override suspend fun getMatchesHistory(id: String,num:String): Result<List<MatchBriefInfo>> {
        var netResult= WebApi.getMatchesHistory(id,num)
        Timber.i("loadUserDate"+netResult)
        if (netResult?.result?.matches!=null){
            return Result.Success(netResult.result!!.matches!!)
        }else{
            return Result.Error(NetErrorException("loadUserDate"))
        }
    }

    override suspend fun getMatchDetails(id: String): Result<MatchDetailInfo> {
        var netResult= WebApi.getMatchDetails(id)
        Timber.i("loadUserDate"+netResult)
        if (netResult?.result!=null){
            return Result.Success(netResult.result!!)
        }else{
            return Result.Error(NetErrorException("loadUserDate"))
        }
    }

//    override suspend fun getMatchesHistoryForShow(id: String,num:String): Result<List<MatchDetailInfo>> {
//        var netResult= WebApi.getMatchesHistory(id,num)
//        Timber.i("loadUserDate"+netResult)
//        if (netResult?.result?.matches!=null){
//            var result = ArrayList<MatchDetailInfo>()
//            for (item in netResult!!.result!!.matches!!){
//                var matchinfo = WebApi.getMatchDetails(item.match_id!!)
//                if (matchinfo?.result!=null){
//                    var _player :PlayerDetailInfo?=null
//                    for (player in matchinfo!!.result!!.players){
//                        if(Util.get64Id(player.account_id).equals(id)){
//                            _player = player
//                            break
//                        }
//                    }
//                    matchinfo!!.result!!.currentPlayer = _player
//
//                    result.add(matchinfo!!.result!!)
//                }
//            }
//            if (result.size>0){
//                return Result.Success(result)
//            }else{
//                return Result.Error(NetErrorException("getMatchesHistoryForShow no data"))
//            }
//        }else{
//            return Result.Error(NetErrorException("getMatchesHistoryForShow error"))
//        }
//    }



}