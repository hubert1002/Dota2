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

package com.deadgame.dota2

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.work.ListenableWorker
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.deadgame.dota2.data.Hero
import com.deadgame.dota2.data.source.DotaRepository
import com.deadgame.dota2.di.DaggerApplicationComponent
import com.deadgame.dota2.util.HEROES_DATA_FILENAME
import com.deadgame.dota2.workers.SeedDatabaseWorker
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import kotlinx.coroutines.*
import timber.log.Timber
import timber.log.Timber.DebugTree
import javax.inject.Inject

/**
 * An [Application] that uses Dagger for Dependency Injection.
 *
 * Also, sets up Timber in the DEBUG BuildConfig. Read Timber's documentation for production setups.
 */
open class DotaApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.factory().create(applicationContext)
    }

    @Inject
    lateinit var repository: DotaRepository


    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(DebugTree())
        CoroutineScope(Dispatchers.IO).launch {
            insertDB()
        }
    }

    private suspend fun insertDB(){
        Timber.i("doWork$repository")
        coroutineScope {
            Timber.i("doWork")
            try {
                applicationContext.assets.open(HEROES_DATA_FILENAME).use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->
                        val plantType = object : TypeToken<List<Hero>>() {}.type
                        val plantList: List<Hero> = Gson().fromJson(jsonReader, plantType)

                        repository.saveHeroes(plantList);
                        ListenableWorker.Result.success()
                    }
                }
            } catch (ex: Exception) {
                Timber.i("doWork"+ex)
                ListenableWorker.Result.failure()
            }
        }
    }


}
