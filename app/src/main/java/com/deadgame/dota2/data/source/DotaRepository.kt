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


/**
 * Interface to the data layer.
 */
interface DotaRepository {
    suspend fun getHeroes(): Result<List<Hero>>

    suspend fun getItems(): Result<List<Item>>
    suspend fun saveHeroes(items:List<Hero>)
    suspend fun getPlayersInfo(ids:List<String>):Result<List<Player>>
    suspend fun getFriendsList(id: String): Result<List<Player>>
    fun getCurrentPlayerId():String?
    suspend fun getMatchesHistory(id: String,num:String): Result<List<MatchBriefInfo>>
    suspend fun getMatchDetails(id: String): Result<MatchDetailInfo>
    suspend fun getMatchesHistoryForShow(id: String,num:String): Result<List<MatchDetailInfo>>
    suspend fun getMatchInfo(id: String): Result<MatchInfo>
}
