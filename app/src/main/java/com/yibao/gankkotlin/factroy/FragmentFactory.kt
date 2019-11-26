package com.yibao.gankkotlin.factroy

import androidx.fragment.app.Fragment
import com.yibao.gankkotlin.base.BaseRvFragment
import com.yibao.gankkotlin.vp.gank.GankFragment
import com.yibao.gankkotlin.vp.gank.app.GankGenericFragment
import com.yibao.gankkotlin.vp.gank.girls.GankGirlsFragment
import com.yibao.gankkotlin.vp.home.HomeFragment
import com.yibao.gankkotlin.vp.me.MeFragment
import java.util.*

/**
 * Author：Sid
 * Des：${TODO}
 * Time:2017/10/20 03:16
 */
class FragmentFactory {
    private val girls = 0//妹子
    private val android = 1//安卓
    private val vedio = 2//视频
    private val app = 3//App
    private val ios = 4//iOS
    private val front = 5//前端
    private val extend = 6//拓展资源
    private var mCacheFragmentMap: MutableMap<Int, Fragment> = HashMap()
    private var mGankFragmentMap: MutableMap<Int, BaseRvFragment> = HashMap()

    fun creatMainFag(position: Int): Fragment? {
        var mainFragment: Fragment? = null
        if (mCacheFragmentMap.containsKey(position)) {
            mainFragment = mCacheFragmentMap[position]
            return mainFragment

        }
        when (position) {
            0 -> mainFragment = GankFragment()
            1 -> mainFragment = HomeFragment()
            2 -> mainFragment = MeFragment()
        }
        mCacheFragmentMap.put(position, mainFragment!!)
        return mainFragment

    }

    fun createFragment(position: Int): BaseRvFragment? {
        var fragment: BaseRvFragment? = null
        //优先从集合中取出来
        if (mGankFragmentMap.containsKey(position)) {
            fragment = mGankFragmentMap[position]
            return fragment
        }

        when (position) {
            girls -> fragment = GankGirlsFragment().newInstance(position)
            android -> fragment = GankGenericFragment().newInstance(position)
            vedio -> fragment = GankGenericFragment().newInstance(position)
            app -> fragment = GankGenericFragment().newInstance(position)
            ios -> fragment = GankGenericFragment().newInstance(position)
            front -> fragment = GankGenericFragment().newInstance(position)
            extend -> fragment = GankGenericFragment().newInstance(position)
        }
        //保存fragment到集合中
        mGankFragmentMap.put(position, fragment!!)
        return fragment
    }
}
