package com.yibao.gankkotlin.util

import com.yibao.gankkotlin.model.home.TimeDate


/**
 *  @项目名：  MyKotlin
 *  @包名：    com.yibao.gankkotlin.util
 *  @文件名:   StringUtil
 *  @author:   Stran
 *  @Email:   www.strangermy@outlook.com / www.stranger98@gmail.com
 *  @创建时间:  2018/1/18 19:11
 *  @描述：    {TODO}
 */
class StringUtil {
    //    2018-01-04
    fun getHistoryDate(s: String): TimeDate {
        val year = s.substring(0, 4)
        val month = s.substring(5, 7)
        val day = s.substring(8, 10)
        return TimeDate(year, month, day)
    }



}