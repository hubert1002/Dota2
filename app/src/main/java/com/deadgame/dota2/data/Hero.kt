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
package com.deadgame.dota2.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.deadgame.dota2.module.hero.HeroesFilter
import java.util.UUID

/**
 * Immutable model class for a Task. In order to compile with Room, we can't use @JvmOverloads to
 * generate multiple constructors.
 *
 * @param title       title of the task
 * @param description description of the task
 * @param isCompleted whether or not this task is completed
 * @param id          id of the task
 */
@Entity(tableName = "heroes")
data class Hero @JvmOverloads constructor(
    @ColumnInfo(name = "localized_name") var localized_name: String = "",
    @ColumnInfo(name = "name") var name: String = "",
    @ColumnInfo(name = "url_full_portrait") var url_full_portrait: String = "",
    @ColumnInfo(name = "url_small_portrait") var url_small_portrait: String = "",
    @ColumnInfo(name = "url_large_portrait") var url_large_portrait: String = "",
    @ColumnInfo(name = "url_vertical_portrait") var url_vertical_portrait: String = "",
    @PrimaryKey @ColumnInfo(name = "id") var id: String = UUID.randomUUID().toString()
) {
    val titleForList: String
        get() = if (localized_name.isNotEmpty()) localized_name else name

    val iconForList:String
        get() = url_vertical_portrait

    val iconForHistory:String
        get() = url_full_portrait

    val type:HeroesFilter
        get() =  HeroesFilter.ALL
}
