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
package com.deadgame.dota2.module.match

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.deadgame.dota2.data.Hero
import com.deadgame.dota2.data.MatchInfo
import com.deadgame.dota2.databinding.DotaHeroListItemBinding
import com.deadgame.dota2.databinding.HeroesItemBinding
import com.deadgame.dota2.databinding.MatchPlayerItem2Binding
import com.deadgame.dota2.databinding.MatchPlayerItemBinding

/**
 * Adapter for the task list. Has a reference to the [ViewModel] to send actions back to it.
 */
class MatchPlayerAdapter(private val viewModel: MatchViewModel) :
    ListAdapter<MatchInfo.PlayersDTO, MatchPlayerAdapter.ViewHolder>(TaskDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(viewModel, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: MatchPlayerItem2Binding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: MatchViewModel, item: MatchInfo.PlayersDTO) {

            binding.viewmodel = viewModel
            binding.playerDto = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MatchPlayerItem2Binding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

/**
 * Callback for calculating the diff between two non-null items in a list.
 *
 * Used by ListAdapter to calculate the minimum number of changes between and old list and a new
 * list that's been passed to `submitList`.
 */
class TaskDiffCallback : DiffUtil.ItemCallback<MatchInfo.PlayersDTO>() {
    override fun areItemsTheSame(oldItem: MatchInfo.PlayersDTO, newItem: MatchInfo.PlayersDTO): Boolean {
        return oldItem.account_id == newItem.account_id
    }

    override fun areContentsTheSame(oldItem: MatchInfo.PlayersDTO, newItem: MatchInfo.PlayersDTO): Boolean {
        return oldItem.account_id == newItem.account_id
    }
}
