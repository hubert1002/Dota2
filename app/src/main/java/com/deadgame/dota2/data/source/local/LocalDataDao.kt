package com.deadgame.dota2.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.deadgame.dota2.data.Hero

/**
 * Created by liuwei04 on 2021/1/8.
 */
@Dao
interface LocalDataDao {
    @Query("SELECT * FROM Heroes")
    suspend fun getHeroes(): List<Hero>

    @Query("SELECT * FROM Heroes WHERE id IN (:ids)")
    suspend fun getHeroes(ids:List<Int>): List<Hero>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(items: List<Hero>)
}