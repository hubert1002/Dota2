package com.deadgame.dota2.data

import com.deadgame.dota2.util.Util

/**
 * Created by liuwei04 on 2021/1/22.
 */
data class FriendInfo  constructor(
        var steamid: String? = null,
        var relationship: String? = null,
        var friend_since:Int = 0
) {

}

