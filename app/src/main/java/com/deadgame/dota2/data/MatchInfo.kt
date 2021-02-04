package com.deadgame.dota2.data

/**
 * Created by liuwei04 on 2021/2/3.
 */
/**
 * Created by liuwei04 on 2021/2/3.
 */
public class MatchInfo {

    var match_id: Long = 0
    var barracks_status_dire = 0
    var barracks_status_radiant = 0
    var chat: List<ChatDTO>? = null
    var cluster = 0
    var cosmetics: CosmeticsDTO? = null
    var dire_score = 0
    var dire_team_id: Any? = null
    var draft_timings: List<*>? = null
    var duration = 0
    var engine = 0
    var first_blood_time = 0
    var game_mode = 0
    var human_players = 0
    var leagueid = 0
    var lobby_type = 0
    var match_seq_num: Long = 0
    var negative_votes = 0
//    var objectives: List<ObjectivesDTO>? = null
    var picks_bans: List<PicksBansDTO>? = null
    var positive_votes = 0
    var radiant_gold_adv: List<Int>? = null
    var radiant_score = 0
    var radiant_team_id: Any? = null
    var isRadiant_win = false
    var radiant_xp_adv: List<Int>? = null
    var skill: Any? = null
    var start_time = 0
    var teamfights: List<TeamfightsDTO>? = null
    var tower_status_dire = 0
    var tower_status_radiant = 0
    var version = 0
    var replay_salt = 0
    var series_id = 0
    var series_type = 0
    var players: List<PlayersDTO>? = null
    var patch = 0
    var region = 0
    var all_word_counts: AllWordCountsDTO? = null
    var my_word_counts: MyWordCountsDTO? = null
    var comeback = 0
    var stomp = 0
    var replay_url: String? = null

    class CosmeticsDTO
    class AllWordCountsDTO
    class MyWordCountsDTO
    class ChatDTO {
        /**
         * time : 389
         * type : chatwheel
         * key : 71
         * slot : 6
         * player_slot : 129
         */
        var time = 0
        var type: String? = null
        var key: String? = null
        var slot = 0
        var player_slot = 0

    }

    class ObjectivesDTO {
        /**
         * time : 11
         * type : CHAT_MESSAGE_FIRSTBLOOD
         * slot : 0
         * key : 7
         * player_slot : 0
         * team : -1
         * unit : npc_dota_creep_badguys_melee
         */
        var time = 0
        var type: String? = null
        var slot = 0
        var key = 0
        var player_slot = 0
        var team = 0
        var unit: String? = null

    }

    class PicksBansDTO {
        /**
         * is_pick : true
         * hero_id : 32
         * team : 0
         * order : 0
         */
        var isIs_pick = false
            private set
        var hero_id = 0
        var team = 0
        var order = 0

        fun setIs_pick(is_pick: Boolean) {
            isIs_pick = is_pick
        }

    }

    class TeamfightsDTO {

        var start = 0
        var end = 0
        var last_death = 0
        var deaths = 0
        var players: List<PlayersDTO>? = null

        class PlayersDTO {
            /**
             * deaths_pos : {}
             * ability_uses : {"riki_blink_strike":3,"riki_tricks_of_the_trade":2,"riki_smoke_screen":1}
             * ability_targets : {}
             * item_uses : {"dust":1}
             * killed : {"npc_dota_hero_sand_king":1,"npc_dota_hero_shadow_shaman":1}
             * deaths : 0
             * buybacks : 0
             * damage : 1248
             * healing : 0
             * gold_delta : 617
             * xp_delta : 1084
             * xp_start : 7300
             * xp_end : 8384
             */
            var killed: KilledDTO? = null
            var deaths = 0
            var buybacks = 0
            var damage = 0
            var healing = 0
            var gold_delta = 0
            var xp_delta = 0
            var xp_start = 0
            var xp_end = 0


            class KilledDTO {
                /**
                 * npc_dota_hero_sand_king : 1
                 * npc_dota_hero_shadow_shaman : 1
                 */
                var npc_dota_hero_sand_king = 0
                var npc_dota_hero_shadow_shaman = 0

            }
        }
    }

    class PlayersDTO {

        var match_id: Long = 0
        var player_slot = 0
        var ability_upgrades_arr: List<Int>? = null
        var ability_uses: AbilityUsesDTO? = null
        var account_id = 0
        var additional_units: Any? = null
        var assists = 0
        var backpack_0 = 0
        var backpack_1 = 0
        var backpack_2 = 0
        var backpack_3: Any? = null
        var camps_stacked = 0
        var creeps_stacked = 0
        var deaths = 0
        var denies = 0
        var dn_t: List<Int>? = null
        var firstblood_claimed = 0
        var gold = 0
        var gold_per_min = 0
        var gold_spent = 0
        var gold_t: List<Int>? = null
        var hero_damage = 0
        var hero_healing = 0
        var hero_id = 0
        var item_0 = 0
        var item_1 = 0
        var item_2 = 0
        var item_3 = 0
        var item_4 = 0
        var item_5 = 0
        var item_neutral = 0
        var kills = 0
        var kills_log: List<KillsLogDTO>? = null
        var last_hits = 0
        var leaver_status = 0
        var level = 0
        var lh_t: List<Int>? = null
        var max_hero_hit: MaxHeroHitDTO? = null
        var net_worth = 0
        var obs_left_log: List<*>? = null
        var obs_log: List<*>? = null
        var obs_placed = 0
        var party_id = 0
        var party_size = 0
        var performance_others: Any? = null
        var permanent_buffs: List<PermanentBuffsDTO>? = null
        var pings = 0
        var isPred_vict = false
        var purchase_log: List<PurchaseLogDTO>? = null
        var isRandomed = false
        var repicked: Any? = null
        var roshans_killed = 0
        var rune_pickups = 0
        var runes_log: List<RunesLogDTO>? = null
        var sen_left_log: List<*>? = null
        var sen_log: List<*>? = null
        var sen_placed = 0
        var stuns = 0.0
        var teamfight_participation = 0.0
        var times: List<Int>? = null
        var tower_damage = 0
        var towers_killed = 0
        var xp_per_min = 0
        var xp_t: List<Int>? = null
        var personaname: String? = null
        var name: Any? = null
        var last_login: Any? = null
        var isRadiant_win = false
        var start_time = 0
        var duration = 0
        var cluster = 0
        var lobby_type = 0
        var game_mode = 0
        var isIs_contributor = false
            private set
        var patch = 0
        var region = 0
        var isRadiant = false
        var win = 0
        var lose = 0
        var total_gold = 0
        var total_xp = 0
        var kills_per_min = 0.0
        var kda = 0
        var abandons = 0
        var neutral_kills = 0
        var tower_kills = 0
        var courier_kills = 0
        var lane_kills = 0
        var hero_kills = 0
        var observer_kills = 0
        var sentry_kills = 0
        var roshan_kills = 0
        var necronomicon_kills = 0
        var ancient_kills = 0
        var buyback_count = 0
        var observer_uses = 0
        var sentry_uses = 0
        var lane_efficiency = 0.0
        var lane_efficiency_pct = 0
        var lane = 0
        var lane_role = 0
        var purchase_tpscroll = 0
        var actions_per_min = 0
        var life_state_dead = 0
        var rank_tier = 0
        var cosmetics: List<CosmeticsDTO>? = null
        var benchmarks: BenchmarksDTO? = null
        var purchase_ward_observer = 0
        var purchase_ward_sentry = 0
        var purchase_rapier = 0

        val fightRate: String
            get() = String.format("参战率:%.2f",teamfight_participation*100)+"%"

        val kdaForShow:String
            get() = String.format("KDA:%.1f",((kills+assists).toFloat()/Math.max(1,deaths)))




        class AbilityUsesDTO {
            /**
             * riki_blink_strike : 78
             * riki_tricks_of_the_trade : 93
             * riki_smoke_screen : 27
             */
            var riki_blink_strike = 0
            var riki_tricks_of_the_trade = 0
            var riki_smoke_screen = 0

        }


        class MaxHeroHitDTO {
            /**
             * type : max_hero_hit
             * time : 3273
             * max : true
             * inflictor : riki_tricks_of_the_trade
             * unit : npc_dota_hero_riki
             * key : npc_dota_hero_invoker
             * value : 1409
             * slot : 0
             * player_slot : 0
             */
            var type: String? = null
            var time = 0
            var isMax = false
            var inflictor: String? = null
            var unit: String? = null
            var key: String? = null
            var value = 0
            var slot = 0
            var player_slot = 0

        }

        class BenchmarksDTO {
            /**
             * gold_per_min : {"raw":634,"pct":0.6572475143903715}
             * xp_per_min : {"raw":692,"pct":0.4097331240188383}
             * kills_per_min : {"raw":0.3033006244424621,"pct":0.5991627420198848}
             * last_hits_per_min : {"raw":8.403211418376449,"pct":0.7687074829931972}
             * hero_damage_per_min : {"raw":940.3568242640499,"pct":0.7111459968602826}
             * hero_healing_per_min : {"raw":0,"pct":0.9717425431711146}
             * tower_damage : {"raw":96,"pct":0.20355834641548928}
             * stuns_per_min : {"raw":0,"pct":0.6331763474620618}
             * lhten : {"raw":41,"pct":0.5798011512297226}
             */
            var gold_per_min: GoldPerMinDTO? = null
            var xp_per_min: XpPerMinDTO? = null
            var kills_per_min: KillsPerMinDTO? = null
            var last_hits_per_min: LastHitsPerMinDTO? = null
            var hero_damage_per_min: HeroDamagePerMinDTO? = null
            var hero_healing_per_min: HeroHealingPerMinDTO? = null
            var tower_damage: TowerDamageDTO? = null
            var stuns_per_min: StunsPerMinDTO? = null
            var lhten: LhtenDTO? = null

            class GoldPerMinDTO {
                /**
                 * raw : 634
                 * pct : 0.6572475143903715
                 */
                var raw = 0.0
                var pct = 0.0

            }

            class XpPerMinDTO {
                /**
                 * raw : 692
                 * pct : 0.4097331240188383
                 */
                var raw = 0.0
                var pct = 0.0

            }

            class KillsPerMinDTO {
                /**
                 * raw : 0.3033006244424621
                 * pct : 0.5991627420198848
                 */
                var raw = 0.0
                var pct = 0.0

            }

            class LastHitsPerMinDTO {
                /**
                 * raw : 8.403211418376449
                 * pct : 0.7687074829931972
                 */
                var raw = 0.0
                var pct = 0.0

            }

            class HeroDamagePerMinDTO {
                /**
                 * raw : 940.3568242640499
                 * pct : 0.7111459968602826
                 */
                var raw = 0.0
                var pct = 0.0

            }

            class HeroHealingPerMinDTO {
                /**
                 * raw : 0
                 * pct : 0.9717425431711146
                 */
                var raw = 0.0
                var pct = 0.0

            }

            class TowerDamageDTO {
                /**
                 * raw : 96
                 * pct : 0.20355834641548928
                 */
                var raw = 0.0
                var pct = 0.0

            }

            class StunsPerMinDTO {
                /**
                 * raw : 0
                 * pct : 0.6331763474620618
                 */
                var raw = 0.0
                var pct = 0.0

            }

            class LhtenDTO {
                /**
                 * raw : 41
                 * pct : 0.5798011512297226
                 */
                var raw = 0.0
                var pct = 0.0

            }
        }

        class KillsLogDTO {
            /**
             * time : 11
             * key : npc_dota_hero_sand_king
             */
            var time = 0.0
            var key: String? = null

        }

        class PermanentBuffsDTO {
            /**
             * permanent_buff : 2
             * stack_count : 0
             */
            var permanent_buff = 0
            var stack_count = 0

        }

        class PurchaseLogDTO {
            /**
             * time : -89
             * key : quelling_blade
             */
            var time = 0
            var key: String? = null

        }

        class RunesLogDTO {
            /**
             * time : 1
             * key : 5
             */
            var time = 0
            var key = 0

        }

        class CosmeticsDTO {
            /**
             * item_id : 6546
             * name : Heir's Gift
             * prefab : wearable
             * creation_date : 2014-05-07T00:00:00.000Z
             * image_inventory : econ/items/rikimaru/rightful_heir_arms/rightful_heir_arms
             * image_path : icons/econ/items/rikimaru/rightful_heir_arms/rightful_heir_arms.bfbd4706aa2ec6778439e91a408f9c9751aab719.png
             * item_description : null
             * item_name : #DOTA_Item_Heirs_Gift
             * item_rarity : null
             * item_type_name : #DOTA_WearableType_Arms
             * used_by_heroes : npc_dota_hero_riki
             */
            var item_id = 0
            var name: String? = null
            var prefab: String? = null
            var creation_date: String? = null
            var image_inventory: String? = null
            var image_path: String? = null
            var item_description: Any? = null
            var item_name: String? = null
            var item_rarity: Any? = null
            var item_type_name: String? = null
            var used_by_heroes: String? = null

        }
    }
}