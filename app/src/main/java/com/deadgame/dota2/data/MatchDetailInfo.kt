package com.deadgame.dota2.data

import com.deadgame.dota2.util.Util

/**
 * Created by liuwei04 on 2021/1/22.
 */
data class MatchDetailInfo  constructor(
        var match_id: Long,
        var match_seq_num: Long,
        var radiant_win: Boolean,
        var duration:Int = 0,
        var tower_status_radiant:Int = 0,
        var tower_status_dire:Int = 0,
        var barracks_status_radiant:Int = 0,
        var barracks_status_dire:Int = 0,
        var pre_game_duration:Int = 0,
        var cluster:Int = 0,
        var first_blood_time:Int = 0,
        var human_players:Int = 0,
        var leagueid:Int = 0,
        var positive_votes:Int = 0,
        var negative_votes:Int = 0,
        var game_mode:Int = 0,
        var flags:Int = 0,
        var engine:Int = 0,
        var radiant_score:Int = 0,
        var dire_score:Int = 0,
        var lobby_type:Int = 0,
        var start_time:Long = 0,
        var players:List<PlayerDetailInfo>,
        var currentPlayer:PlayerDetailInfo?
) {

    val matchType:String
        get() = CommonData.getLobby(lobby_type)

    val matchTime:String
        get() = CommonData.formRelativeDate(start_time)

    val isWin:Boolean
        get() = CommonData.isRadiantFromSlot(currentPlayer!!.player_slot) && radiant_win ||
                !CommonData.isRadiantFromSlot(currentPlayer!!.player_slot) && !radiant_win

}

data class PlayerDetailInfo  constructor(
        var account_id: Long ,
        var hero_id: Int,
        var player_slot:Int = 0,
        var item_0:Int = 0,
        var item_1:Int = 0,
        var item_2:Int = 0,
        var item_3:Int = 0,
        var item_4:Int = 0,
        var item_5:Int = 0,
        var backpack_0:Int = 0,
        var backpack_1:Int = 0,
        var backpack_2:Int = 0,
        var item_neutral:Int = 0,
        var kills:Int = 0,
        var deaths:Int = 0,
        var assists:Int = 0,
        var leaver_status:Int = 0,
        var last_hits:Int = 0,
        var denies:Int = 0,
        var gold_per_min:Int = 0,
        var xp_per_min:Int = 0,
        var level:Int = 0,
        var heroInfo :Hero?
) {

    val teamForShow:String
        get() = CommonData.teamForShow(player_slot)

    val killsForShow:String
        get() = "K $kills"

    val deathsForShow:String
        get() = "D $deaths"

    val assistsForShow:String
        get() = "A $assists"

}