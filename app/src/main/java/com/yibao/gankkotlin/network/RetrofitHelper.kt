package com.yibao.gankkotlin.network

import com.google.gson.GsonBuilder
import com.yibao.gankkotlin.MyApplication
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Author：Sid
 * Des：${TODO}
 * Time:2017/10/17 00:24
 */
class RetrofitHelper {

    companion object {
        private var url: String = "http://gank.io/"
        private var myServices: GankServices

        init {
            val gson = GsonBuilder().setLenient().create()
            myServices = Retrofit.Builder().baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(MyApplication().getOkhttpClient()).build().create(GankServices::class.java)


        }


    }

    fun getGankApi(baseUrl: String): GankServices {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(MyApplication().getOkhttpClient()).build().create(GankServices::class.java)

    }




}