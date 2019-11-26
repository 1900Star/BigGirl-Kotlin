package com.yibao.gankkotlin.vp.gank.girl

import android.content.Context
import androidx.viewpager.widget.ViewPager
import android.widget.Scroller

/**
 * Author：Sid
 * Des：${TODO}
 * Time:2017/8/18 19:39
 */
class PagerScroller(context: Context) : Scroller(context) {

    private var mScrollDuration: Int = 0

     fun setDuration(duration: Int) {
        this.mScrollDuration = duration
    }


    override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int) {
        super.startScroll(startX, startY, dx, dy, mScrollDuration)

    }

    override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) {
        super.startScroll(startX, startY, dx, dy, mScrollDuration)
    }


    fun initViewPagerScroll(viewPager: ViewPager, duration: Int) {
        try {
            val field = ViewPager::class.java.getDeclaredField("mScroller")
            field.isAccessible = true
            setDuration(duration)
            field.set(viewPager, this)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}
