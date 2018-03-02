package com.yibao.gankkotlin.base

import android.os.Build
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.animation.DecelerateInterpolator
import kotlinx.android.synthetic.main.view_toolbar.*


/**
 *  @项目名：  MyKotlin
 *  @包名：    com.yibao.gankkotlin.base
 *  @文件名:   ToolbarActivity
 *  @author:   Stran
 *  @Email:   www.strangermy@outlook.com / www.strangermy98@gmail.com
 *  @创建时间:  2018/1/14 16:39
 *  @描述：    {TODO}
 */
abstract class ToolbarActivity : AppCompatActivity() {

    protected var mIsHidden = false
    protected abstract val layoutId: Int
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        app_bar_layout as AppBarLayout
        toolbar as Toolbar
        if (toolbar == null || app_bar_layout == null) {
            throw IllegalStateException(
                    "The subclass of ToolbarActivity must contain a toolbar.")
        }
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)


        if (Build.VERSION.SDK_INT >= 21) {
            app_bar_layout!!.elevation = 10.6f
        }
        initView(savedInstanceState)
        initData()
        initListener()

    }

    abstract fun initListener()

    abstract fun initView(savedInstanceState: Bundle?)


    abstract fun initData()


    protected fun setAppBarAlpha(alpha: Float) {
        app_bar_layout!!.alpha = alpha
    }


    protected fun hideOrShowToolbar() {
        app_bar_layout!!.animate()
                .translationY(if (mIsHidden) 0F else (-app_bar_layout!!.height).toFloat())
                .setInterpolator(DecelerateInterpolator(2F))
                .start()
        mIsHidden = !mIsHidden
    }

}