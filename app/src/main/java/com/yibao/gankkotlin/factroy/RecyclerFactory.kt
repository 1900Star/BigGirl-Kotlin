package com.yibao.gankkotlin.factroy

import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.widget.LinearLayout


/**
 *  @项目名：  MyKotlin
 *  @包名：    com.yibao.mykotlin.base
 *  @文件名:   RecyclerFactory
 *  @author:   Stran
 *  @Email:   www.strangermy@outlook.com / www.stranger98@gmail.com
 *  @创建时间:  2018/1/12 14:56
 *  @描述：    {TODO}
 */
class RecyclerFactory {
    private val RECYCLERVIEW_NORMAL = 1
    private val RECYCLERVIEW_STAGGERED = 2

    fun creatRecyclerView(context: FragmentActivity?, type: Int,
                          adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>): RecyclerView {
        val recyclerView = RecyclerView(context)
        if (type == RECYCLERVIEW_NORMAL) {
            val params = LinearLayout.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                    RecyclerView.LayoutParams.MATCH_PARENT)

            val manager = LinearLayoutManager(context)
            manager.orientation = LinearLayoutManager.VERTICAL
            recyclerView.isVerticalScrollBarEnabled = true
            recyclerView.layoutManager = manager
            recyclerView.layoutParams = params

        } else {
            val params = StaggeredGridLayoutManager.LayoutParams(
                    RecyclerView.LayoutParams.MATCH_PARENT,
                    RecyclerView.LayoutParams.MATCH_PARENT)
            val manager = StaggeredGridLayoutManager(type,
                    StaggeredGridLayoutManager.VERTICAL)
            manager.orientation = StaggeredGridLayoutManager.VERTICAL
            recyclerView.layoutManager = manager
            recyclerView.layoutParams = params
        }
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
        return recyclerView
    }


}