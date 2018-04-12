package com.yibao.gankkotlin

import android.app.Application
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

/**
 *  @项目名：  MyKotlin
 *  @包名：
 *  @文件名:   com.yibao.mykotlin.MyApplication
 *  @author:   Stran
 *  @Email:   www.strangermy@outlook.com / www.strangermy98@gmail.com
 *  @创建时间:  2018/1/12 16:00
 *  @描述：    {TODO}
 */
class MyApplication : Application() {
    fun getOkhttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.SECONDS)
                .writeTimeout(3, TimeUnit.SECONDS)
                .readTimeout(3, TimeUnit.SECONDS)
                .build()


    }


}