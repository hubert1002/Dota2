package com.deadgame.dota2.data

import com.deadgame.dota2.util.Util

/**
 * Created by liuwei04 on 2021/1/22.
 */
data class MatchBriefInfo  constructor(
        var match_id: String? = null,
        var match_seq_num: String? = null,
        var radiant_team_id:Int = 0,
        var start_time:Long = 0,
        var lobby_type:Int = 0,
        var dire_team_id:Int = 0,
        var players:List<PlayerBriefInfo>
) {

}

data class PlayerBriefInfo  constructor(
        var account_id: String? = null,
        var hero_id: String? = null,
        var player_slot:Int = 0
) {

}