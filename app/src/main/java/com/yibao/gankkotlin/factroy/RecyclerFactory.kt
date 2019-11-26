package com.yibao.gankkotlin.factroy

import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import android.widget.LinearLayout
import com.yibao.gankkotlin.base.BaseRvAdapter
import com.yibao.gankkotlin.model.Meizi


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
                          adapter: BaseRvAdapter<Meizi>): RecyclerView {
        val recyclerView = RecyclerView(context!!)
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