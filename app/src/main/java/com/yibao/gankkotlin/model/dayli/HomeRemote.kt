package com.yibao.gankkotlin.model

import com.yibao.gankkotlin.model.dayli.ResultBeans
import com.yibao.gankkotlin.network.RetrofitHelper
import com.yibao.gankkotlin.util.Constract
import com.yibao.gankkotlin.util.StringUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 *  @项目名：  MyKotlin
 *  @包名：    com.yibao.mykotlin.model.gank
 *  @文件名:   GankGenericeRemote
 *  @author:   Stran
 *  @Email:   www.strangermy@outlook.com / www.stranger98@gmail.com
 *  @创建时间:  2018/1/12 19:58
 *  @描述：    {TODO}
 */
class HomeRemote : HomeSource {

    override fun getGankHistoryDate(position: Int, callBack: HomeSource.GankHistoryCallbak) {
        RetrofitHelper().getGankApi(Constract().gankBaseUrl)
                .getGankHistoryDate(Constract().gankHistory)
                .subscribeOn(Schedulers.io())
                .map { it.results }
                .map { StringUtil().getHistoryDate(it[position]) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    callBack.loadHistoryDate(it)
                }
    }

    override fun getGankDayData(year: String, month: String, day: String, callBack: HomeSource.GankDayLoadCallbak) {
        RetrofitHelper().getGankApi(Constract().gankBaseUrl)
                .getGankDayData(year, month, day)
                .subscribeOn(Schedulers.io())
                .map { addData(it.results!!) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    callBack.loadData(it as ArrayList<Meizi>)
                }


    }

    private fun addData(results: ResultBeans): List<Meizi> {
        val mList = ArrayList<Meizi>()
        if (results.福利 != null) {
            mList.addAll(results.福利)
        }
        if (results.Android != null) {
            mList.addAll(results.Android)
        }
        if (results.iOS != null) {
            mList.addAll(results.iOS)

        }
        if (results.App != null) {
            mList.addAll(results.App)
        }
        if (results.休息视频 != null) {

            mList.addAll(results.休息视频)
        }
        if (results.拓展资源 != null) {

            mList.addAll(results.拓展资源)
        }
        if (results.瞎推荐 != null) {
            mList.addAll(results.瞎推荐)

        }
        return mList
    }

}



