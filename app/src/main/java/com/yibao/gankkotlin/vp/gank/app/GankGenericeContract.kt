package com.yibao.gankkotlin.vp.gank.app

import com.yibao.gankkotlin.base.BasePresenter
import com.yibao.gankkotlin.base.BaseView
import com.yibao.gankkotlin.model.Meizi


/**
 *  @项目名：  MyKotlin
 *  @包名：    com.yibao.mykotlin.vp.gank.app
 *  @文件名:   GankGenericeContract
 *  @author:   Stran
 *  @Email:   www.strangermy@outlook.com / www.stranger98@gmail.com
 *  @创建时间:  2018/1/12 21:13
 *  @描述：    {TODO}
 */
interface GankGenericeContract {
    interface Veiw : BaseView<Presenter> {
        fun loadData(list: ArrayList<Meizi>)
        fun loadMoreData(list: ArrayList<Meizi>)
        fun refreshData(list: ArrayList<Meizi>)
        fun loadError()
        fun loadNormal()
    }

    interface Presenter : BasePresenter {
        fun loadData(size: Int, page: Int, loadType: String, loadStatus: Int)
    }

}
