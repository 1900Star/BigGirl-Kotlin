package com.yibao.gankkotlin.util

import android.support.design.widget.Snackbar
import android.support.design.widget.Snackbar.LENGTH_LONG
import android.support.design.widget.Snackbar.make
import android.view.View


/**
 * 作者：Stran on 2017/3/28 01:31
 * 描述：${各种需求的Snakbar}
 * 邮箱：strangermy@outlook.com
 */
class SnakbarUtil {


    /**
     * 下载成功提示
     */
    fun showSuccessView(view: View) {
        val snackbar = make(view, "图片保存成功 -_-", Snackbar.LENGTH_LONG)
        snackbar.view
                .setBackgroundColor(ColorUtil().successColor)
        snackbar.show()

    }

    /**
     * 收藏成功提示
     */
    fun favoriteSuccessView(view: View, str: String) {
        val snackbar = make(view, str, Snackbar.LENGTH_LONG)
        snackbar.view
                .setBackgroundColor(ColorUtil().successColor)
        snackbar.show()

    }

    /**
     * 收藏失败提示
     */
    fun favoriteFailView(view: View, str: String) {
        val snackbar = make(view, str, Snackbar.LENGTH_LONG)
        snackbar.view
                .setBackgroundColor(ColorUtil().errorColor)
        snackbar.show()

    }

    /**
     * 图片已经下载过啦
     */
    fun picAlreadyExists(view: View) {
        val snackbar = make(view, "图片已经下载过啦  -_-", Snackbar.LENGTH_LONG)
        snackbar.view
                .setBackgroundColor(ColorUtil().picAlreadyExists)
        snackbar.show()

    }

    /**
     * 下载失败提示
     */
    fun showDownPicFail(view: View) {

        val snackbar = make(view, "图片保存失败 -_-", Snackbar.LENGTH_LONG)
        snackbar.view
                .setBackgroundColor(ColorUtil().errorColor)
        snackbar.show()

    }

    /**
     * 分享失败提示
     */
    fun showSharePicFail(view: View) {

        val snackbar = make(view, "分享失败 -_-", Snackbar.LENGTH_LONG)
        snackbar.view
                .setBackgroundColor(ColorUtil().errorColor)
        snackbar.show()

    }


    /**
     * GoogleMap提示
     */
    fun mapPoint(view: View) {

        val snackbar = make(view, "当前设备不支持完整的谷歌服务!", Snackbar.LENGTH_LONG)
        snackbar.view
                .setBackgroundColor(ColorUtil().picAlreadyExists)
        snackbar.show()

    }

    /**
     * 网络异常提示
     */
    fun netErrors(view: View) {

        val snackbar = make(view, "网络异常，请检查您的网络连接 -_-", LENGTH_LONG)
        snackbar.view
                .setBackgroundColor(ColorUtil().errorColor)

        snackbar.show()
    }

    /**
     * 网络异常长时提示
     */
    fun netErrorsLong(view: View) {

        val snackbar = make(view, "网络异常，请检查您的网络连接 ,之后重试 ！-_-", Snackbar.LENGTH_INDEFINITE)
        snackbar.view
                .setBackgroundColor(ColorUtil().errorColor)

        snackbar.show()
    }

    /**
     * 退出程序
     */
    fun finishActivity(view: View) {

        val snackbar = make(view, "再按一次我就离开了 -_-", Snackbar.LENGTH_SHORT)
        snackbar.view
                .setBackgroundColor(ColorUtil().exitColor)
        snackbar.show()

    }

    /**
     * 关闭Snakbar
     */
    fun setWallpaer(view: View) {
        val snackbar = make(view, "壁纸设置成功  -_-", Snackbar.LENGTH_LONG)
        snackbar.view
                .setBackgroundColor(ColorUtil().successColor)
        snackbar.show()

    }


}
