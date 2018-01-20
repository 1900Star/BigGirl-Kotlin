package com.yibao.gankkotlin.model.dayli


/**
 *  @项目名：  MyKotlin
 *  @包名：    com.yibao.gankkotlin.model.dayli
 *  @文件名:   `RemoteData`
 *  @author:   Stran
 *  @Email:   www.strangermy@outlook.com / www.stranger98@gmail.com
 *  @创建时间:  2018/1/18 15:56
 *  @描述：    {TODO}
 */
data class RemoteData<T>(var error: Boolean, var results: ArrayList<T>)