package com.yibao.gankkotlin.vp.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.Button

/**
 * Author：Sid
 * Des：${TODO}
 * Time:2017/8/12 21:32
 */
class ProgressBtn : Button {
    private var isProgressEnable = true
    private var max = 0
    var pbColor = Color.GRAY

    var downProgress: Int = 0
    /**
     * 设置进度的当前值
     */
    //重绘进度


    private var mDrawable: Drawable? = null


    fun setColor(color: Int) {
        this.pbColor = color
    }

    /**
     * 是否允许有进度
     */
    fun setProgressEnable(progressEnable: Boolean) {
        isProgressEnable = progressEnable
    }

    /**
     * 设置进度的最大值
     */
    fun setMax(max: Int) {
        this.max = max
    }

    fun setProgress(ps: Int) {
        this.downProgress = ps
        postInvalidate()
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun onDraw(canvas: Canvas) {
        if (isProgressEnable) {
            if (mDrawable == null) {
                mDrawable = ColorDrawable(Color.argb(255, 255, 64, 129))
            }
            val left = 0
            val top = 0
            val right = (this.downProgress * 1.0f / max * measuredWidth + .5f).toInt()
            val bottom = bottom
            mDrawable!!.setBounds(left, top, right, bottom)

            mDrawable!!.draw(canvas)

        }
        super.onDraw(canvas)
    }
}
