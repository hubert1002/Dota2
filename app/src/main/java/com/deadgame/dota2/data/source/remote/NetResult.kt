package com.deadgame.dota2.data.source.remote

import java.net.CacheResponse

/**
 * Created by liuwei04 on 2021/1/18.
 */
data class NetResult<T> (
//    var result  :T?,
    var result  :NetResultObj<T>?,
    var response: NetResultObj<T>?,
    var friendslist: NetResultObj<T>?
)

data class NetResultObj<T>(
    var items  :List<T>?,
    var players  :List<T>?,
    var friends  :List<T>?,
    var matches  :List<T>?,
    var status : Int?,
    var num_results:Int?,
    var total_results:Int?,
    var results_remaining:Int?
)

data class NetResult2<T> (
        var result  :T?
)