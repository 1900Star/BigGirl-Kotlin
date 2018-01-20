package com.yibao.gankkotlin.vp.home

import com.yibao.gankkotlin.base.BasePresenter
import com.yibao.gankkotlin.base.BaseView
import com.yibao.gankkotlin.model.Meizi
import com.yibao.gankkotlin.model.dayli.TimeDate


/**
 *  @项目名：  MyKotlin
 *  @包名：    com.yibao.mykotlin.vp.gank.app
 *  @文件名:   GankGenericeContract
 *  @author:   Stran
 *  @Email:   www.strangermy@outlook.com / www.stranger98@gmail.com
 *  @创建时间:  2018/1/12 21:13
 *  @描述：    {TODO}
 */
interface HomeFragmentContract {
    interface Veiw : BaseView<Presenter> {
        fun loadHistoryDate(timeDate : TimeDate)
        fun loadData(list: ArrayList<Meizi>)
        fun loadMoreData(list: ArrayList<Meizi>)
        fun refreshData(list: ArrayList<Meizi>)
        fun loadError()
        fun loadNormal()
    }

    interface Presenter : BasePresenter {
        fun getDate(position:Int)
        fun loadData(year: String, month: String, day: String, loadStatus: Int)
    }

}
