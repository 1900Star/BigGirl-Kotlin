package com.yibao.gankkotlin.vp.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

import com.yibao.gankkotlin.R


/**
 * 作者：Stran on 2017/3/27 16:55
 * 描述：${自定义ProgressBar}
 * 邮箱：strangermy@outlook.com
 * @author Stran
 */
class ProgressView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : LinearLayout(context, attrs) {
    private val mIvIcon: FloatingActionButton
    private val mTvNote: TextView? = null

    private var isProgressEnable = true
    private var mMax = 100
    private var mProgress: Int = 0
    private var mOval: RectF? = null
    private var mPaint: Paint? = null

    /**
     * 设置是否允许进度
     *
     * @param progressEnable d
     */
    fun setProgressEnable(progressEnable: Boolean) {
        isProgressEnable = progressEnable
    }

    /**
     * 设置进度的最大值
     *
     * @param max
     */
    fun setMax(max: Int) {
        mMax = max
    }

    /**
     * 设置进度的当前值
     *
     * @param progress
     */
    fun setProgress(progress: Int) {
        mProgress = progress
        //重绘
        //        invalidate()        这个方法会报子线程修改UI异常，
        postInvalidate()
    }

    /**
     * 修改图标的内容
     */
    fun setIcon(resId: Int) {
        mIvIcon.setImageResource(resId)
    }

    /**
     * 修改文本的内容
     */
    fun setNote(content: String) {
        mTvNote!!.text = content
    }

    init {
        //挂载布局

        val view = View.inflate(context, R.layout.progress_btn, this)
        mIvIcon = view.findViewById(R.id.fab_progress)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)//绘制背景(透明)
    }

    override fun dispatchDraw(canvas: Canvas) {
        super.dispatchDraw(canvas)//绘制图标和文字
        val bottom = mIvIcon.bottom
        val top = mIvIcon.top
        val left = mIvIcon.left
        val right = mIvIcon.right
        if (isProgressEnable) {
            if (mOval == null) {

                mOval = RectF(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat())

            }
            val startAngle = -90f
            val sweepAngle = mProgress * 1.0f / mMax * 360
            //是否以图片的中心为圆点
//            val useCenter = false
            if (mPaint == null) {
                mPaint = Paint()
                //                如果需要画扇形就将此行注释，并且useCenter为ture
                mPaint!!.style = Paint.Style.STROKE
                mPaint!!.strokeWidth = 10f
                mPaint!!.isAntiAlias = true
                mPaint!!.color = Color.argb(255, 90, 180, 63)
            }
            canvas.drawArc(mOval!!, startAngle, sweepAngle, false, mPaint!!)
        }

    }
}
