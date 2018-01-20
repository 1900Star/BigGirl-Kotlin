package com.yibao.gankkotlin.vp.home

import android.os.Bundle
import com.yibao.gankkotlin.base.BaseRvFragment
import com.yibao.gankkotlin.model.Meizi
import com.yibao.gankkotlin.model.dayli.TimeDate
import com.yibao.gankkotlin.vp.main.MainActivity

/**
 * Author：Sid
 * Des：${TODO}
 * Time:2017/10/19 21:21
 */
class HomeFragment : BaseRvFragment(), HomeFragmentContract.Veiw {

    private var mPresenter = HomeFragmentPresenter(this)
    private lateinit var mAdapter: HomeAdatper
    override fun processLogic(savedInstanceState: Bundle?) {
        println()
    }

    override fun onLazyLoadData() {
        super.onLazyLoadData()
        mPresenter.getDate(mPostition)

    }

    override fun loadHistoryDate(timeDate: TimeDate) {
        println("========LoadStatus========  "+mLoadStatus)
        mPresenter.loadData(timeDate.year, timeDate.month, timeDate.day, mLoadStatus)
    }

    override fun loadData(list: ArrayList<Meizi>) {
        val url = list[0].url
        ((activity) as MainActivity).setCllospingImage(url)
        mAdapter = HomeAdatper(activity,list)
        val recyclerView = getRecyclerView(1, mAdapter)
        mFagContent.addView(recyclerView)
        mSwipeRefresh.isRefreshing = false

    }

    override fun refreshData() {
        mPresenter.getDate(mPostition)

    }

    override fun loadMoreData() {
        mPresenter.getDate(mPostition)
    }

    override fun refreshData(list: ArrayList<Meizi>) {
        mAdapter.clear()
        mAdapter.AddHeader(list)
        mAdapter.notifyDataSetChanged()
    }

    override fun loadMoreData(list: ArrayList<Meizi>) {
        mAdapter.AddFooter(list)
        mAdapter.notifyDataSetChanged()
    }

    override fun loadError() {

    }

    override fun loadNormal() {

    }


    override fun setPresenter(presenter: HomeFragmentContract.Presenter) {
        mPresenter = presenter as HomeFragmentPresenter

    }
}