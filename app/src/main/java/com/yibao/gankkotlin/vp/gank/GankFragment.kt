package com.yibao.gankkotlin.vp.gank

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yibao.gankkotlin.R
import com.yibao.gankkotlin.util.Constract


/**
 * Author：Sid
 * Des：${Gank的主页面，包含多个数据 页面}
 * Time:2017/10/19 21:21
 */
class GankFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = View.inflate(activity, R.layout.fragment_gank, null)
        initView(view)
        return view

    }

    private fun initView(view: View) {
        val gankPager: ViewPager = view.findViewById(R.id.viewpager_gank)
        val tablayout: TabLayout = view.findViewById(R.id.tablayout_gank)
        tablayout.setupWithViewPager(gankPager)
        val adapter = GankPagerAdapter(fragmentManager)
        gankPager.offscreenPageLimit = Constract().staarrTitle.size
        gankPager.adapter = adapter
    }


}