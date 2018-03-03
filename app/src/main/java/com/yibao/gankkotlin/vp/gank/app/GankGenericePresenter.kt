package com.yibao.gankkotlin.vp.gank.app

import com.yibao.gankkotlin.base.BaseRvFragment
import com.yibao.gankkotlin.model.GankGenericeRemote
import com.yibao.gankkotlin.model.GankGenericeSource
import com.yibao.gankkotlin.model.Meizi
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
class GankGenericePresenter(private val mView: BaseRvFragment) : GankGenericeContract.Presenter {
    private val gankRemote = GankGenericeRemote()

    init {
        mView.setPresenter(this)
    }

    override fun start(loadType: String, loadStatus: Int) {
        loadData(20, 1, loadType, loadStatus)
    }

    override fun loadData(size: Int, page: Int, loadType: String, loadStatus: Int) {
        gankRemote.getGankGenericeData(page, size, loadType, object : GankGenericeSource.GankGenericeLoadCallbak {
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