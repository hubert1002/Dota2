package com.deadgame.dota2.data

import com.deadgame.dota2.util.Util

/**
 * Created by liuwei04 on 2021/1/22.
 */
data class Player  constructor(
        var steamid: String? = null,
        var communityvisibilitystate:Int = 0,
        var profilestate:Int = 0,
        val personaname: String? = null,
        var lastlogoff:Int = 0,
        var commentpermission:Int = 0,
        val profileurl: String? = null,
        val avatar: String? = null,
        val avatarmedium: String? = null,
        val avatarfull: String? = null,
        val realname: String? = null,
        val primaryclanid: String? = null,
        val loccountrycode: String? = null,
        val locstatecode: String? = null,
        var personastate:Int = 0,
        var timecreated:Int = 0,
        var personastateflags:Int = 0,
        var loccityid:Int = 0
) {
    val title: String
        get() = personaname!!

    val stateFroShow:String
        get() = Util.getState(personastate)

    val steamidFroShow:String
        get() = if (steamid!=null) Util.get32Id(steamid!!.toLong()) else "test"

        val lastLoginTime:String
                get() = if (lastlogoff!=0) "上次登陆：" +Util.formRelativeDate(lastlogoff.toLong()) else "test"

}

