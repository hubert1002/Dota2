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

import com.deadgame.dota2.data.Hero
import com.deadgame.dota2.data.Item
import com.deadgame.dota2.data.Result
import com.deadgame.dota2.di.ApplicationModule
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.lang.Exception
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
        return@withContext fetchFromRemoteOrLocal()
    }

    override suspend fun getItems(): Result<List<Item>> {
        val remote = remoteDS.getItems()
//        val local = localDS.getItems()
        return remote
    }

    override suspend fun saveHeroes(items: List<Hero>) = withContext(ioDispatcher){
        localDS.saveHeroes(items)
    }

    private suspend fun fetchFromRemoteOrLocal(): Result<List<Hero>> {
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

}
