package com.deadgame.dota2.data.source.local

import com.deadgame.dota2.data.Hero
import com.deadgame.dota2.data.Item
import com.deadgame.dota2.data.Result
import com.deadgame.dota2.data.source.DotaDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

/**
 * Created by liuwei04 on 2021/1/8.
 */
class  LocalDataSource internal constructor(private val LocalDataDao :LocalDataDao,private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) :DotaDataSource{
    override suspend fun getHeroes(): Result<List<Hero>> = withContext(ioDispatcher){
        return@withContext try {
            Result.Success(LocalDataDao.getHeroes())
        }catch (e:Exception){
            Result.Error(e)
        }
    }

    override suspend fun getItems(): Result<List<Item>> {
        TODO()
    }

    override suspend fun saveHeroes(heroes: List<Hero>) {
        LocalDataDao.insertTask(heroes)
    }
}