package com.yibao.gankkotlin.util

import com.yibao.gankkotlin.model.dayli.TimeDate
import java.util.*


/**
 *  @项目名：  MyKotlin
 *  @包名：    com.yibao.gankkotlin.util
 *  @文件名:   TimeUtil
 *  @author:   Stran
 *  @Email:   www.strangermy@outlook.com / www.stranger98@gmail.com
 *  @创建时间:  2018/1/17 22:35
 *  @描述：    {TODO}
 */
class TimeUtil {
    fun getTimeNowThroughCalendar(): TimeDate {
        //使用默认时区和语言环境获得一个日历。
        val rightNow = Calendar.getInstance()
        /*用Calendar的get(int field)方法返回给定日历字段的值。
        HOUR 用于 12 小时制时钟 (0 - 11)，HOUR_OF_DAY 用于 24 小时制时钟。*/
        val year = rightNow.get(Calendar.YEAR)
        val month = rightNow.get(Calendar.MONTH) + 1 //第一个月从0开始，所以得到月份＋1
        val day = rightNow.get(Calendar.DAY_OF_MONTH)
        return TimeDate(year.toString(), month.toString(), day.toString())
    }
}