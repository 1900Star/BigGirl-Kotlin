package com.yibao.gankkotlin.util

import android.content.Context
import android.net.ConnectivityManager

/**
 * Author：Sid
 * Des：${TODO}
 * Time:2017/4/7 15:52
 */
class NetworkUtil {
    /**
     * 判断是否有网络连接
     * @param context
     * @return
     */
    fun isNetworkConnected(context: Context?): Boolean {

        if (context != null) {
            val manager = context.getSystemService(
                    Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val info = manager.activeNetworkInfo
            if (info != null) {
                return info.isAvailable
            }
        }
        return false
    }


}
