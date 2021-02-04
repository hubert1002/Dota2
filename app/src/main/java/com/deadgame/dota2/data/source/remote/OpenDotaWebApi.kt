package com.deadgame.dota2.data.source.remote

import androidx.room.FtsOptions
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
import retrofit2.http.Path
import retrofit2.http.Query
import timber.log.Timber

/**
 * Created by liuwei04 on 2021/1/18.
 */
object OpenDotaWebApi {
    private const val  URL = "https://api.opendota.com/api/"

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

    fun getMatchInfo(id: String) : MatchInfo? {
        apiService.getMatchInfo(id)?.apply {
            var res = execute()
            Timber.i("getMatchDetails"+res)
            return res.body()
        }
        return null;
    }

    interface ApiService {
        @GET("matches/{matchId}")
        fun getMatchInfo(@Path("matchId") key: String?): Call<MatchInfo?>?
    }
}

