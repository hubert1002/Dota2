package com.deadgame.dota2.data.source.remote

import com.deadgame.dota2.data.Item
import com.google.gson.GsonBuilder
import com.squareup.okhttp.OkHttpClient
import retrofit.GsonConverterFactory
import retrofit.Retrofit
import retrofit.RxJavaCallAdapterFactory
import retrofit.http.GET
import retrofit.http.Query
import rx.Observable
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import timber.log.Timber

/**
 * Created by liuwei04 on 2021/1/18.
 */
object WebApi {
    private const val  URL = "http://api.steampowered.com/"
    private const val  KEY = "A7CAFA33562B6310ACDC0C3864B9DC1B"

    private const val RELATION = "friend"
    private const val LANGUAGE = "zh"

    private var apiService: ApiService
    private val gson = GsonBuilder().create()

    init {
        val retrofit: Retrofit = Retrofit.Builder().baseUrl(URL)
                .client(OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
        apiService = retrofit.create(ApiService::class.java)
    }


    fun getItems(observer: Observer<NetResult<Item>?>?) {
        apiService.getItems(KEY, LANGUAGE)
            ?.subscribeOn(Schedulers.newThread())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(observer)
    }

    fun getItemsSync():NetResult<Item>?{
//        return apiService.getItemsSync(KEY, LANGUAGE)
        return null
    }


    interface ApiService {
       /*
        //ISteamUser/GetPlayerSummaries/v0002/?key=A7CAFA33562B6310ACDC0C3864B9DC1B&steamid=76561198089905448
        @GET("ISteamUser/GetPlayerSummaries/v0002/")
        fun getUsers(@Query("key") key: String?,
                     @Query("steamids") id: String?): Observable<User?>?

        //ISteamUser/GetFriendList/v1/?key=A7CAFA33562B6310ACDC0C3864B9DC1B&steamid=76561198089905448&relationship=friend
        @GET("ISteamUser/GetFriendList/v1/")
        fun getFriends(@Query("key") key: String?,
                       @Query("steamid") id: String?,
                       @Query("relationship") relationship: String?): Observable<FriendResult?>?

        //IDOTA2Match_570/GetMatchHistory/v001/?key=A7CAFA33562B6310ACDC0C3864B9DC1B&account_id=129639720&matches_requested=3
        @GET("IDOTA2Match_570/GetMatchHistory/v001/")
        fun getMatchesHistory(@Query("key") key: String?,
                              @Query("account_id") id: String?,
                              @Query("matches_requested") num: String?): Observable<MatchesHistory?>?

        //IDOTA2Match_570/GetMatchDetails/V001/?key=A7CAFA33562B6310ACDC0C3864B9DC1B&match_id=2199956955
        @GET("IDOTA2Match_570/GetMatchDetails/V001/")
        fun getMatchDetails(@Query("key") key: String?,
                            @Query("match_id") id: String?): Observable<Match?>?
*/
        //IEconDOTA2_570/GetGameItems/V001/?key=A7CAFA33562B6310ACDC0C3864B9DC1B&language=zh
        @GET("IEconDOTA2_570/GetGameItems/V001/")
        fun getItems(@Query("key") key: String?,
                     @Query("language") zh: String?): Observable<NetResult<Item>?>?

        @GET("IEconDOTA2_570/GetGameItems/V001/")
        fun getItemsSync(@Query("key") key: String?,
                     @Query("language") zh: String?): NetResult<Item>

    }
}

