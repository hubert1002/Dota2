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

package com.deadgame.dota2.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.deadgame.dota2.data.source.DefaultDotaRepository
import com.deadgame.dota2.data.source.DotaDataSource
import com.deadgame.dota2.data.source.DotaRepository
import com.deadgame.dota2.data.source.local.DotaDatabase
import com.deadgame.dota2.data.source.local.LocalDataSource
import com.deadgame.dota2.data.source.remote.RemoteDataSource
import com.deadgame.dota2.util.DATABASE_NAME
import com.deadgame.dota2.workers.SeedDatabaseWorker
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import timber.log.Timber
import javax.inject.Qualifier
import javax.inject.Singleton
import kotlin.annotation.AnnotationRetention.RUNTIME


@Module(includes = [ApplicationModuleBinds::class])
object ApplicationModule {

    @Qualifier
    @Retention(RUNTIME)
    annotation class DotaRemoteDataSource

    @Qualifier
    @Retention(RUNTIME)
    annotation class DotaLocalDataSource

    @JvmStatic
    @Singleton
    @DotaRemoteDataSource
    @Provides
    fun provideTasksRemoteDataSource(): DotaDataSource {
        Timber.i("provideTasksRemoteDataSource")
        return RemoteDataSource
    }

    @JvmStatic
    @Singleton
    @DotaLocalDataSource
    @Provides
    fun provideTasksLocalDataSource(
        database: DotaDatabase,
        ioDispatcher: CoroutineDispatcher
    ): DotaDataSource {
        Timber.i("provideTasksLocalDataSource")
        return LocalDataSource(
            database.localDataDao(), ioDispatcher
        )
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideDataBase(context: Context): DotaDatabase {
        Timber.i("provideDataBase")
        return Room.databaseBuilder(
            context.applicationContext,
            DotaDatabase::class.java,
            DATABASE_NAME
        ).addCallback(object :RoomDatabase.Callback(){
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                Timber.i("provideDataBase onCreate")
//        val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build()
//        WorkManager.getInstance(this).enqueue(request)
            }
        }).build()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO
}

@Module
abstract class ApplicationModuleBinds {

    @Singleton
    @Binds
    abstract fun bindRepository(repo: DefaultDotaRepository): DotaRepository
}
