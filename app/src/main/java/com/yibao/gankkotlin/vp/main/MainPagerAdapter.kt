package com.yibao.gankkotlin.vp.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import android.view.View
import android.view.ViewGroup
import com.yibao.gankkotlin.factroy.FragmentFactory

/**
 * Author：Sid
 * Des：${TODO}
 * Time:2017/10/20 02:52
 */
class MainPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment = FragmentFactory().creatMainFag(position)!!

    override fun getCount(): Int = 3


    override fun isViewFromObject(view: View, `object`: Any): Boolean =
            (`object` as Fragment).view === view

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }
}