package com.yibao.gankkotlin.util

import android.content.Context
import android.os.Environment


/**
 *  @项目名：  MyKotlin
 *  @包名：    com.yibao.gankkotlin.util
 *  @文件名:   FileUtil
 *  @author:   Stran
 *  @Email:   www.strangermy@outlook.com / www.stranger98@gmail.com
 *  @创建时间:  2018/1/14 21:50
 *  @描述：    {TODO}
 */
class FileUtil {

    /**
     * 获取缓存文件夹
     *   isExternalStorageEmulated()设备的外存是否是用内存模拟的，是则返回true
     * @param context
     * @return
     */

    fun getDiskCacheDir(context: Context): String {

        //isExternalStorageEmulated()设备的外存是否是用内存模拟的，是则返回true
        return if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState() || !Environment.isExternalStorageEmulated()) {
            context.externalCacheDir
                    .absolutePath
        } else {
            context.cacheDir?.absolutePath!!
        }
    }
}