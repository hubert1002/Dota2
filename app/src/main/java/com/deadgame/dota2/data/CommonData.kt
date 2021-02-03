package com.deadgame.dota2.data

import android.text.format.DateUtils

/**
 * Created by liuwei04 on 2021/2/2.
 */

object CommonData {
    val  HERO_NAME = arrayOf( //  1       2      3       4       5       6       7        8       9        10
            "敌法师", "斧王", "痛苦之源", "血魔", "水晶室女", "卓尔游侠", "撼地神牛", "主宰", "月之女祭司", "影魔",
            "变体精灵", "幻影长矛手", "精灵龙", "屠夫", "闪电幽魂", "沙王", "风暴之灵", "流浪剑客", "山岭巨人", "复仇之魂",
            "风行者", "宙斯", "船长", "无", "秀逗魔导师", "恶魔巫师", "暗影萨满", "鱼人守卫", "潮汐猎人", "巫医",
            "巫妖", "隐刺", "谜团", "修补匠", "矮人火枪手", "死灵法师", "术士", "兽王", "痛苦女王", "剧毒术士",
            "虚空假面", "骷髅王", "死亡先知", "幻影刺客", "瘟疫法师", "圣堂刺客", "冥界亚龙", "月之骑士", "龙骑士", "暗影牧师",
            "发条技师", "受折磨的灵魂", "先知", "噬魂鬼", "黑暗贤者", "骷髅射手", "全能骑士", "魅惑魔女", "神灵武士", "暗夜魔王",
            "育母蜘蛛", "赏金猎人", "地穴编织者", "双头龙", "蝙蝠骑士", "陈", "幽鬼", "极寒幽魂", "末日守卫", "熊战士",
            "灵魂行者", "矮人直升机", "炼金术士", "祈求者", "沉默术士", "黑耀毁灭者", "狼人", "熊猫酒仙", "暗影恶魔", "德鲁伊",
            "混沌骑士", "地卜师", "树精卫士", "食人魔法师", "不朽尸王", "拉比克", "干扰者", "司夜刺客", "娜迦海妖", "光之守卫",
            "小精灵", "死灵飞龙", "鱼人夜行者", "美杜莎", "巨魔战将", "半人马酋长", "半人猛犸", "地精收割机", "刚背兽", "巨牙海民",
            "天怒法师", "亚巴顿", "上古巨神", "军团指挥官", "地精工程师", "灰烬之灵", "大地之灵", "无", "灵魂守卫", "凤凰",
            "神谕者", "寒冬飞龙", "弧光"
    )

    fun isRadiantFromSlot(slot: Int): Boolean {
        return slot < 5
    }

    fun teamForShow(slot: Int):String{
        return if (isRadiantFromSlot(slot)) {
            "天辉"
        }else{
            "夜魇"
        }
    }

    fun getHeroName(id:Int):String{
        if(id-1>= HERO_NAME.size){
            return "unKnownHeroId $id"
        }else{
            return CommonData.HERO_NAME[id - 1]
        }
    }

    fun getLobby(lobby: Int): String {
        return when (lobby) {
            0 -> "公共比赛"
            1 -> "练习赛"
            2 -> "联赛"
            3 -> "教程"
            4 -> "人机对战"
            5 -> "团队比赛"
            6 -> "队列solo"
            7 -> "天梯比赛"
            8 -> "中路solo"
            else -> "无效的"
        }
    }


    fun formRelativeDate(microSecond: Long): String {
        val milliSecond = 1000 * microSecond
        val now = System.currentTimeMillis()
        val date: CharSequence
        date = if (now - milliSecond >= DateUtils.MINUTE_IN_MILLIS) {
            DateUtils.getRelativeTimeSpanString(milliSecond,
                    now,
                    DateUtils.MINUTE_IN_MILLIS,
                    DateUtils.FORMAT_ABBREV_RELATIVE)
        } else {
            "刚刚"
        }
        return date.toString()
    }

}
