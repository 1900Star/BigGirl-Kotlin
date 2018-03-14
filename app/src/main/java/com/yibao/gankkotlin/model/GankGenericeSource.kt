package com.yibao.gankkotlin.model


/**
 *  @项目名：  MyKotlin
 *  @包名：    com.yibao.mykotlin.model.gank
 *  @文件名:   GankGenericeSource
 *  @author:   Stran
 *  @Email:   www.strangermy@outlook.com / www.stranger98@gmail.com
 *  @创建时间:  2018/1/12 19:50
 *  @描述：    {TODO}
 */
interface GankGenericeSource {
    interface GankGenericeLoadCallbak {
        fun loadData(list: ArrayList<Meizi>)
        fun onDataNotAvailable()
    }

    fun getGankGenericeData(page: Int, size: Int, loadType: String, callBack: GankGenericeLoadCallbak)


}