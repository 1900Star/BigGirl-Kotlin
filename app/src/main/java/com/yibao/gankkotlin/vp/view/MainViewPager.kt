package com.yibao.gankkotlin.vp.view

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

/**
 * Author：Sid
 * Des：${TODO}
 * Time:2017/10/20 18:45
 */
class MainViewPager : ViewPager {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean = false

//    override fun onTouchEvent(event: MotionEvent): Boolean = false
}
