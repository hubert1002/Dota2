package com.deadgame.dota2.data.source.remote

/**
 * Created by liuwei04 on 2021/1/18.
 */
data class NetResult<T> (
    var result  :NetResultObj<T>
)

data class NetResultObj<T>(
    var items  :List<T>,
    var status : Int
)