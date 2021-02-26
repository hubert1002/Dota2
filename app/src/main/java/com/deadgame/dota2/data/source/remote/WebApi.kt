package com.deadgame.dota2.data.source.remote

import com.deadgame.dota2.data.*
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
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


    fun test(){
        val client = OkHttpClient().newBuilder()
            .build()
        val mediaType =
            "multipart/form-data; boundary=----WebKitFormBoundaryUcuj6QzA25Stpgst".toMediaTypeOrNull()
        val body: RequestBody = MultipartBody.Builder().setType(MultipartBody.FORM)
            .addFormDataPart("appId", "ZX0001")
            .addFormDataPart("startTime", "2021-02-16")
            .addFormDataPart("endTime", "2021-02-23")
            .addFormDataPart("byDhid", "true")
            .addFormDataPart("statDim", "byDay")
            .addFormDataPart("filterResolveSheet", "0")
            .build()
        val request: Request = Request.Builder()
            .url("http://cr-admin.51y5.net/kepler-dashboard//bugly/api/crash/trenddata")
            .method("POST", body)
            .addHeader("X-BUGLY-ANR", "false")
            .addHeader(
                "Cookie",
                "JSESSIONID=1E563044F7D8CA017575DE1A7C5F7379; OUTFOX_SEARCH_USER_ID_NCOO=419280410.70044017; bugly.project.code=ZX0001; JSESSIONID=FA79555020057140ED7D5AF2A2F797AF"
            )
            .addHeader("X-BUGLY-APPID", "ZX0001")
            .addHeader(
                "User-Agent",
                "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.182 Mobile Safari/537.36"
            )
            .addHeader("Referer", "http://cr-admin.51y5.net/kepler-dashboard/bugly/index")
            .addHeader("Proxy-Connection", "keep-alive")
            .addHeader("Origin", "http://cr-admin.51y5.net")
            .addHeader("Host", "cr-admin.51y5.net")
            .addHeader(
                "Content-Type",
                "multipart/form-data; boundary=----WebKitFormBoundaryUcuj6QzA25Stpgst"
            )
            .addHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8")
            .addHeader("Accept-Encoding", "gzip, deflate")
            .addHeader("Accept", "application/json, text/plain, */*")
            .build()
        val response = client.newCall(request).execute()
        Timber.i("test"+response)
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
            return runCatching {
                execute()
            }.getOrNull()?.body()
        }
        return null
    }


    fun getFriends(id:String): NetResult<FriendInfo>? {
        apiService.getFriends(KEY,id,RELATION)?.apply {
            return runCatching {
                execute()
            }.getOrNull()?.body()
        }
        return null
    }

    fun getMatchesHistory(id:String,num:String): NetResult<MatchBriefInfo>? {
        apiService.getMatchesHistory(KEY, id, num)?.apply {
            return runCatching {
                execute()
            }.getOrNull()?.body()
        }
        return null
    }

    fun getMatchDetails(id: String) : NetResult2<MatchDetailInfo>? {
        apiService.getMatchDetails(KEY, id)?.apply {
            return runCatching {
                execute()
            }.getOrNull()?.body()
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

