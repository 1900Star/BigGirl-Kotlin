package com.yibao.gankkotlin.network

import com.yibao.gankkotlin.model.Meizi
import com.yibao.gankkotlin.model.dayli.GankDayData
import com.yibao.gankkotlin.model.dayli.RemoteData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


/**
 *  @项目名：  MyKotlin
 *  @包名：    com.yibao.gankkotlin.network
 *  @文件名:   GankServices
 *  @author:   Stran
 *  @Email:   www.strangermy@outlook.com / www.stranger98@gmail.com
 *  @创建时间:  2018/1/17 16:56
 *  @描述：    {TODO}
 */

interface GankServices {

    @GET("api/data/{type}/{count}/{page}")
    fun getGril(@Path("type") type: String,
                @Path("count") count: Int,
                @Path("page") page: Int): Observable<RemoteData<Meizi>>


    @GET("api/day/{year}/{month}/{day}")
    fun getGankDayData(@Path("year") year: String,
                       @Path("month") month: String,
                       @Path("day") day: String): Observable<GankDayData>

    @GET("api/day/{history}")
    fun getGankHistoryDate(@Path("history") history: String): Observable<RemoteData<String>>
}
