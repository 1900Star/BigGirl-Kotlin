package com.yibao.gankkotlin.base

import android.content.Context
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yibao.gankkotlin.R


/**
 *  @项目名：  MyKotlin
 *  @包名：    com.yibao.mykotlin.base
 *  @文件名:   BaseRvAdapter
 *  @author:   Stran
 *  @Email:   www.strangermy@outlook.com / www.stranger98@gmail.com
 *  @创建时间:  2018/1/12 14:59
 *  @描述：    {TODO}
 */
abstract class BaseRvAdapter<T>(context: Context?, list: MutableList<T>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val minItemNum = 10
    protected abstract val layoutId: Int
    protected var mContext = context
    var mList: MutableList<T>


    val data: List<T>?
        get() = mList

    init {
        mList = list

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {
        if (viewType == TYPE_ITEM) {

            val view = LayoutInflater.from(parent.context)
                    .inflate(layoutId, parent, false)
            return getViewHolder(view)
        } else if (viewType == TYPE_FOOTER) {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.load_more_view, parent, false)
            return LoadMoreHolder(view)
        }

        return null
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //将绑定视图交给子类去做
        bindView(holder, mList[position])
    }

    protected abstract fun bindView(holder: RecyclerView.ViewHolder, t: T)

    protected abstract fun getViewHolder(view: View): RecyclerView.ViewHolder


    override fun getItemCount(): Int = mList.size


    override fun getItemViewType(position: Int): Int {
        return if (position > minItemNum && position == itemCount - 1) {
            TYPE_FOOTER
        } else TYPE_ITEM
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder?) {
        super.onViewAttachedToWindow(holder)
        val params = holder!!.itemView.layoutParams
        if (params != null && params is StaggeredGridLayoutManager.LayoutParams) {
            params.isFullSpan = holder.layoutPosition == itemCount - 1
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {
        super.onAttachedToRecyclerView(recyclerView)

        val manager = recyclerView!!.layoutManager
        if (manager is GridLayoutManager) {
            manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (getItemViewType(position) == TYPE_FOOTER)
                        manager.spanCount
                    else
                        1
                }
            }
        }
    }

    fun clear() {
        mList.clear()
        notifyDataSetChanged()
    }


    fun addHeader(list: List<T>) {
        mList.addAll(list)
        notifyDataSetChanged()
    }

    fun addFooter(list: List<T>) {
        list
                .filterNot { mList.contains(it) }
                .forEach { mList.add(it) }
        notifyDataSetChanged()

    }

    fun setNewData(data: MutableList<T>) {
        this.mList = data
        notifyDataSetChanged()
    }

    fun addData(position: Int, data: List<T>) {
        this.mList.addAll(position, data)
        this.notifyItemRangeInserted(position, data.size)
    }


    internal class LoadMoreHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {

        }
    }

    companion object {

        private val TYPE_ITEM = 0
        private val TYPE_FOOTER = 1
    }


}