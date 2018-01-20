package com.yibao.gankkotlin.vp.gank.girl

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import com.yibao.gankkotlin.model.Meizi
import com.yibao.gankkotlin.util.GlideUtil
import com.yibao.gankkotlin.util.ImageUtil


/**
 *  @项目名：  MyKotlin
 *  @包名：    com.yibao.gankkotlin.vp.gank.girl
 *  @文件名:   GirlPagerAdapter
 *  @author:   Stran
 *  @Email:   www.strangermy@outlook.com / www.stranger98@gmail.com
 *  @创建时间:  2018/1/14 17:42
 *  @描述：    {TODO}
 */
class GirlPagerAdapter(private val context: Context, private val list: ArrayList<Meizi>?) : PagerAdapter() {
    lateinit var primaryItem: View

    override fun getCount(): Int {

        return list?.size ?: 0

    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val view = ImageUtil().creatZoomView(context)
        val url = list!![position].url
        GlideUtil().loadPic(url, view)
        view.setOnClickListener({
            if (context is OnGirlClickListener) {
                (context as OnGirlClickListener).hintToolbar()
            }
        })
        container.addView(view)
        return view
    }

    override fun setPrimaryItem(container: ViewGroup, position: Int, `object`: Any) {
        primaryItem = `object` as View
    }

    interface OnGirlClickListener {
        fun hintToolbar()
    }
}