package com.yibao.gankkotlin.vp.gank

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import android.view.View
import android.view.ViewGroup
import com.yibao.gankkotlin.factroy.FragmentFactory
import com.yibao.gankkotlin.util.Constract

/**
 * Author：Sid
 * Des：${TODO}
 * Time:2017/10/20 02:52
 */
class GankPagerAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm!!) {

    override fun getItem(position: Int): Fragment = FragmentFactory().createFragment(position)!!
    override fun getCount(): Int = Constract().staarrTitle.size


    override fun isViewFromObject(view: View, `object`: Any): Boolean =
            (`object` as Fragment).view === view


    override fun getPageTitle(position: Int): CharSequence? = Constract().staarrTitle[position]


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }
}