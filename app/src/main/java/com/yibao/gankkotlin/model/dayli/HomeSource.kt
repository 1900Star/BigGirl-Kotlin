package com.yibao.gankkotlin.model

import com.yibao.gankkotlin.model.dayli.TimeDate


/**
 *  @项目名：  MyKotlin
 *  @包名：    com.yibao.mykotlin.model.gank
 *  @文件名:   GankGenericeSource
 *  @author:   Stran
 *  @Email:   www.strangermy@outlook.com / www.stranger98@gmail.com
 *  @创建时间:  2018/1/12 19:50
 *  @描述：    {TODO}
 */
interface HomeSource {
    interface GankDayLoadCallbak {
        fun loadData(list: ArrayList<Meizi>)
        fun onDataNotAvailable()
    }

    fun getGankDayData(year: String, month: String, day: String, callBack: GankDayLoadCallbak)


    interface GankHistoryCallbak {
        fun loadHistoryDate(timeDate: TimeDate)
        fun onDataNotAvailable()
    }

    fun getGankHistoryDate(position: Int, callBack: GankHistoryCallbak)


}