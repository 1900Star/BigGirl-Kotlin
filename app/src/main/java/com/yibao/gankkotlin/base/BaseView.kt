package com.yibao.gankkotlin.base

import com.yibao.gankkotlin.model.Meizi


/**
 *  @项目名：  MyKotlin
 *  @包名：    com.yibao.mykotlin.base
 *  @文件名:   BaseView
 *  @author:   Stran
 *  @Email:   www.strangermy@outlook.com / www.stranger98@gmail.com
 *  @创建时间:  2018/1/12 20:26
 *  @描述：    {TODO}
 */
interface BaseView<in T> {
    fun setPresenter(presenter: T)
    fun loadData(list: ArrayList<Meizi>)
    fun loadMoreData(list: ArrayList<Meizi>)
    fun refreshData(list: ArrayList<Meizi>)
    fun loadError()
    fun loadNormal()

}