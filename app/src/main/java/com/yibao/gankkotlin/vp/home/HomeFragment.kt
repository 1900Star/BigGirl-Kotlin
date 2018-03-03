package com.yibao.gankkotlin.vp.home

import android.os.Bundle
import android.view.View
import com.yibao.gankkotlin.base.BasePresenter
import com.yibao.gankkotlin.base.BaseRvFragment
import com.yibao.gankkotlin.model.Meizi
import com.yibao.gankkotlin.util.Constract
import com.yibao.gankkotlin.vp.main.MainActivity

/**
 * Author：Sid
 * Des：${TODO}
 * Time:2017/10/19 21:21
 */
class HomeFragment : BaseRvFragment() {
    override fun againLoadData() {
        mPresenter.getDate(mPostition, Constract().loadDataStatus)
    }


    private var mPresenter = HomeFragmentPresenter(this)
    private lateinit var mAdapter: HomeAdatper
    override fun processLogic(savedInstanceState: Bundle?) {
        println()
    }

    override fun onLazyLoadData() {
        super.onLazyLoadData()
        mPresenter.getDate(mPostition, mLoadStatus)

    }


    override fun loadData(list: ArrayList<Meizi>) {
        mErrorView.visibility = View.GONE
        val url = list[0].url
        ((activity) as MainActivity).setCllospingImage(url)
        mAdapter = HomeAdatper(activity, list)
        val recyclerView = getRecyclerView(1, mAdapter)
        mFagContent.addView(recyclerView)
        mSwipeRefresh.isRefreshing = false
        mFagContent.visibility = View.VISIBLE

    }

    override fun onRefreshData() {
        mPresenter.getDate(mPostition, mLoadStatus)

    }

    override fun loadMoreData() {
        mPresenter.getDate(mPostition, mLoadStatus)
    }

    override fun refreshData(list: ArrayList<Meizi>) {
        mAdapter.clear()
        mAdapter.addHeader(list)

    }

    override fun loadMoreData(list: ArrayList<Meizi>) {
        mAdapter.addFooter(list)
    }


    override fun setPresenter(presenter: BasePresenter) {
        mPresenter = presenter as HomeFragmentPresenter


    }
}