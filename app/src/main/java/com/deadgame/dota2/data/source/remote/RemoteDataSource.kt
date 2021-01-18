package com.deadgame.dota2.data.source.remote

import com.deadgame.dota2.data.Hero
import com.deadgame.dota2.data.Item
import com.deadgame.dota2.data.Result
import com.deadgame.dota2.data.source.DotaDataSource
import rx.Observer
import timber.log.Timber
import java.lang.Exception
import java.util.*

/**
 * Created by liuwei04 on 2021/1/8.
 */
object RemoteDataSource : DotaDataSource {
    private var TASKS_SERVICE_DATA = LinkedList<Hero>()

    override suspend fun getHeroes(): Result<List<Hero>> {
        return Result.Success(TASKS_SERVICE_DATA)
    }

    override suspend fun getItems(): Result<List<Item>> {
        var test= WebApi.getItems(object : Observer<NetResult<Item>?> {
            override fun onCompleted() {}
            override fun onError(e: Throwable) {}
            override fun onNext(t: NetResult<Item>?) {
                Timber.i("=$t")
            }
        })
        var netResult= WebApi.getItemsSync()
        if (netResult!=null&&netResult.result.status==200){
            return Result.Success(netResult.result.items)
        }else{
            return Result.Error(Exception("test"))
        }
    }

    override suspend fun saveHeroes(heroes: List<Hero>) {
        TODO("Not yet implemented")
    }
}