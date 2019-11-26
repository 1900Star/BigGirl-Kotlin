package com.yibao.gankkotlin.vp.view

import android.content.Context
import android.graphics.PointF
import androidx.viewpager.widget.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent


/**
 * Author：Sid
 * Des：${TODO}
 * Time:2017/10/20 18:45
 */
class GankViewPager : ViewPager {
    private val downPoint = PointF()

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)


    override fun onTouchEvent(evt: MotionEvent): Boolean {
        when (evt.action) {
            MotionEvent.ACTION_DOWN -> {
                // 记录按下时候的坐标
                downPoint.x = evt.x
                downPoint.y = evt.y
                if (this.childCount > 1) { //有内容，多于1个时
                    // 通知其父控件，现在进行的是本控件的操作，不允许拦截
                    parent.requestDisallowInterceptTouchEvent(true)
                }
            }
            MotionEvent.ACTION_MOVE -> if (this.childCount > 1) { //有内容，多于1个时
                // 通知其父控件，现在进行的是本控件的操作，不允许拦截
                parent.requestDisallowInterceptTouchEvent(true)
            }
            MotionEvent.ACTION_UP ->
                // 在up时判断是否按下和松手的坐标为一个点
                if (PointF.length(evt.x - downPoint.x, evt.y - downPoint.y) < 5.0.toFloat()) {
                    return true
                }
        }
        return super.onTouchEvent(evt)
    }


}