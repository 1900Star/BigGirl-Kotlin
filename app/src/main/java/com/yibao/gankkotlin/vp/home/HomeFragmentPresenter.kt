package com.yibao.gankkotlin.vp.home

import com.yibao.gankkotlin.base.BaseRvFragment
import com.yibao.gankkotlin.model.HomeRemote
import com.yibao.gankkotlin.model.HomeSource
import com.yibao.gankkotlin.model.Meizi
import com.yibao.gankkotlin.model.dayli.TimeDate
import com.yibao.gankkotlin.util.Constract


/**
 *  @项目名：  MyKotlin
 *  @包名：    com.yibao.mykotlin.vp.gank.app
 *  @文件名:   GankGenericePresenter
 *  @author:   Stran
 *  @Email:   www.strangermy@outlook.com / www.stranger98@gmail.com
 *  @创建时间:  2018/1/12 20:40
 *  @描述：    {TODO}
 */
class HomeFragmentPresenter(private val mView: BaseRvFragment) : HomeFragmentContract.Presenter {

    private val homeRemote = HomeRemote()

    init {
        mView.setPresenter(this)
    }

    override fun start(loadType: String, loadStatus: Int) {}

    override fun getDate(position: Int, loadStatus: Int) {
        homeRemote.getGankHistoryDate(position, object : HomeSource.GankHistoryCallbak {
            override fun loadHistoryDate(timeDate: TimeDate) {
                loadData(timeDate.year, timeDate.month, timeDate.day, loadStatus)
            }

            override fun onDataNotAvailable() {
                mView.loadNormal()

            }

        })

    }

    override fun loadData(year: String, month: String, day: String, loadStatus: Int) {
        homeRemote.getGankDayData(year, month, day, object : HomeSource.GankDayLoadCallbak {
            override fun loadData(list: ArrayList<Meizi>) {
                when (loadStatus) {
                    Constract().refreshDataStatus -> mView.refreshData(list)
                    Constract().loadDataStatus -> mView.loadData(list)
                    Constract().loadDataMore -> mView.loadMoreData(list)
                }
            }

            override fun onDataNotAvailable() {
                mView.loadError()
            }

        })

    }

    override fun subcrible() {
    }

}