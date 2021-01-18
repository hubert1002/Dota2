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
 *
 */
data class Item  constructor(
    @ColumnInfo(name = "localized_name") var localized_name: String = "",
    @ColumnInfo(name = "name") var name: String = "",
    @ColumnInfo(name = "cost") var cost: Int,
    @ColumnInfo(name = "secret_shop") var secret_shop: Int,
    @ColumnInfo(name = "side_shop") var side_shop: Int,
    @ColumnInfo(name = "recipe") var recipe: Int,
    @PrimaryKey @ColumnInfo(name = "id") var id: String = UUID.randomUUID().toString()
) {
    val titleForList: String
        get() = if (localized_name.isNotEmpty()) localized_name else name

    val iconForList:String
        get() = ""
}
