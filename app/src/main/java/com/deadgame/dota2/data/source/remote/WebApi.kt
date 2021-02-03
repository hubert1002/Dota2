package com.deadgame.dota2.data.source.remote

import com.deadgame.dota2.data.*
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
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

        val loggingInterceptor = HttpLoggingInterceptor(object :HttpLoggingInterceptor.Logger{
            override fun log(message: String) {
               Timber.i(message)
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
        val retrofit: Retrofit = Retrofit.Builder().baseUrl(URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        apiService = retrofit.create(ApiService::class.java)
    }


    fun getItems(observer: Observer<NetResult<Item>?>?) {
        apiService.getItems(KEY, LANGUAGE)
            ?.subscribeOn(Schedulers.newThread())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(observer!!)
    }

    fun getItemsSync():NetResult<Item>?{
//        return apiService.getItemsSync(KEY, LANGUAGE)
        return null
    }

    fun getUsers(ids:List<String>): NetResult<Player>? {
        var sb = StringBuilder()
        for ((index,e) in ids.withIndex()){
            sb.append(e)
            if(index != ids.size-1){
                sb.append(",")
            }
        }
        apiService.getUsers(KEY,sb.toString())?.apply {
            var res =  execute()
            Timber.i("getUsers"+res)
            return res.body()
        }
        return null
    }


    fun getFriends(id:String): NetResult<FriendInfo>? {
        apiService.getFriends(KEY,id,RELATION)?.apply {
            var res =  execute()
            Timber.i("getUsers"+res)
            return res.body()
        }
        return null
    }

    fun getMatchesHistory(id:String,num:String): NetResult<MatchBriefInfo>? {
        apiService.getMatchesHistory(KEY, id, num)?.apply {
            var res =  execute()
            Timber.i("getMatchesHistory"+res)
            return res.body()
        }
        return null
    }

    fun getMatchDetails(id: String) : NetResult2<MatchDetailInfo>? {
        apiService.getMatchDetails(KEY, id)?.apply {
            var res = execute()
            Timber.i("getMatchDetails"+res)
            return res.body()
        }
        return null;
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

        //ISteamUser/GetPlayerSummaries/v0002/?key=A7CAFA33562B6310ACDC0C3864B9DC1B&steamids=76561198089905448
        @GET("ISteamUser/GetPlayerSummaries/v0002/")
        fun getUsers(@Query("key") key: String?,
                     @Query("steamids") id: String?): Call<NetResult<Player>>?

        //ISteamUser/GetFriendList/v1/?key=A7CAFA33562B6310ACDC0C3864B9DC1B&steamid=76561198089905448&relationship=friend
        @GET("ISteamUser/GetFriendList/v1/")
        fun getFriends(@Query("key") key: String?,
                       @Query("steamid") id: String?,
                       @Query("relationship") relationship: String?): Call<NetResult<FriendInfo>>?

        //IDOTA2Match_570/GetMatchHistory/v001/?key=A7CAFA33562B6310ACDC0C3864B9DC1B&account_id=129639720&matches_requested=3
        @GET("IDOTA2Match_570/GetMatchHistory/v001/")
        fun getMatchesHistory(@Query("key") key: String?,
                              @Query("account_id") id: String?,
                              @Query("matches_requested") num: String?): Call<NetResult<MatchBriefInfo>>?

        //IDOTA2Match_570/GetMatchDetails/V001/?key=A7CAFA33562B6310ACDC0C3864B9DC1B&match_id=2199956955
        @GET("IDOTA2Match_570/GetMatchDetails/V001/")
        fun getMatchDetails(@Query("key") key: String?,
                            @Query("match_id") id: String?): Call<NetResult2<MatchDetailInfo>>?

    }
}

