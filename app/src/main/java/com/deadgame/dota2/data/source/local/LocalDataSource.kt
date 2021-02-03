package com.deadgame.dota2.data.source.local

import com.deadgame.dota2.data.*
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

    override suspend fun getHeroInfo(ids: List<Int>): Result<List<Hero>> = withContext(ioDispatcher){
        return@withContext try {
            Result.Success(LocalDataDao.getHeroes(ids))
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

    override suspend fun getPlayersInfo(ids: List<String>): Result<List<Player>> {
        TODO("Not yet implemented")
    }

    override suspend fun getFriendsList(id: String): Result<List<Player>> {
        TODO("Not yet implemented")
    }

    override suspend fun getMatchesHistory(id: String, num: String): Result<List<MatchBriefInfo>> {
        TODO("Not yet implemented")
    }

    override suspend fun getMatchDetails(id: String): Result<MatchDetailInfo> {
        TODO("Not yet implemented")
    }

//    override suspend fun getMatchesHistoryForShow(id: String, num: String): Result<List<MatchDetailInfo>> {
//        TODO("Not yet implemented")
//    }

}