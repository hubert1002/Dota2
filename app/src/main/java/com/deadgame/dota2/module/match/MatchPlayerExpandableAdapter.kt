package com.deadgame.dota2.module.match

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import com.deadgame.dota2.data.MatchInfo
import com.deadgame.dota2.databinding.MatchPlayerItem2Binding
import com.deadgame.dota2.databinding.MatchPlayerItemChildBinding
import com.deadgame.dota2.databinding.MatchPlayerItemParentBinding

/**
 * Created by liuwei04 on 2021/2/7.
 */
class MatchPlayerExpandableAdapter(private val viewModel: MatchViewModel,var players:List<MatchInfo.PlayersDTO> ,var context: Context): BaseExpandableListAdapter() {

    fun updateList( list:List<MatchInfo.PlayersDTO>){
        players = list
        notifyDataSetChanged()
    }

    override fun getGroup(groupPosition: Int): Any {
        return players[groupPosition]
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
       return true
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var holder :ParentViewHolder = if(convertView==null){
            ParentViewHolder.from(context)
        }else{
            convertView.tag as ParentViewHolder
        }

        holder.bind(viewModel, players[groupPosition])
       return  holder.binding.root
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return 1
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return players[groupPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var holder :ChildViewHolder = if(convertView==null){
            ChildViewHolder.from(context)
        }else{
            convertView.tag as ChildViewHolder
        }
        holder.bind(viewModel, players[groupPosition])
        return  holder.binding.root
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getGroupCount(): Int {
        return players.size
    }



    class ParentViewHolder private constructor(val binding: MatchPlayerItemParentBinding){

        fun bind(viewModel: MatchViewModel, item: MatchInfo.PlayersDTO) {
            binding.viewmodel = viewModel
            binding.playerDto = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(context: Context): ParentViewHolder {
                val layoutInflater = LayoutInflater.from(context)
                val binding = MatchPlayerItemParentBinding.inflate(layoutInflater, null, false)
                var result = ParentViewHolder(binding)
                binding.root.tag = result
                return result
            }
        }
    }

    class ChildViewHolder private constructor(val binding: MatchPlayerItemChildBinding){

        fun bind(viewModel: MatchViewModel, item: MatchInfo.PlayersDTO) {
            binding.viewmodel = viewModel
            binding.playerDto = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(context: Context): ChildViewHolder {
                val layoutInflater = LayoutInflater.from(context)
                val binding = MatchPlayerItemChildBinding.inflate(layoutInflater, null, false)
                var result = ChildViewHolder(binding)
                binding.root.tag = result
                return result
            }
        }
    }
}