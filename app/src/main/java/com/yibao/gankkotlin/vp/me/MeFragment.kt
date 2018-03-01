package com.yibao.gankkotlin.vp.me

import android.os.Bundle
import com.yibao.gankkotlin.R
import com.yibao.gankkotlin.base.BaseFragment

/**
 * Author：Sid
 * Des：${TODO}
 * Time:2017/10/19 21:21
 */
class MeFragment : BaseFragment() {
    override fun initView(savedInstanceState: Bundle?) {
        setContentView(R.layout.about_me)
    }

    override fun processLogic(savedInstanceState: Bundle?) {
        println("MeFragment")
    }


}