package com.yibao.gankkotlin.model

import com.yibao.gankkotlin.model.home.ResultBeans
import com.yibao.gankkotlin.model.home.TimeDate
import com.yibao.gankkotlin.network.RetrofitHelper
import com.yibao.gankkotlin.util.Constract
import com.yibao.gankkotlin.util.StringUtil
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
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

    fun getGankHistoryDates(position: Int, callBack: HomeSource.GankHistoryCallbak) {
        RetrofitHelper().getGankApi(Constract().gankBaseUrl)
        RetrofitHelper ().getGankApi(Constract().gankBaseUrl)
                .getGankHistoryDate(Constract().gankHistory)
                .subscribeOn(Schedulers.io())
                .map { it.results }
                .map { StringUtil().getHistoryDate(it[position]) }
//                .flatMap(Function {  })




                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<TimeDate> {
                    override fun onNext(t: TimeDate) {
                        callBack.loadHistoryDate(t)
                    }

                    override fun onSubscribe(d: Disposable) {

                    }


                    override fun onError(e: Throwable) {
                        callBack.onDataNotAvailable()

                    }

                    override fun onComplete() {

                    }
                })


    }

    override fun getGankHistoryDate(position: Int, callBack: HomeSource.GankHistoryCallbak) {
        RetrofitHelper().getGankApi(Constract().gankBaseUrl)
                .getGankHistoryDate(Constract().gankHistory)
                .subscribeOn(Schedulers.io())
                .map { it.results }
                .map { StringUtil().getHistoryDate(it[position]) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<TimeDate> {
                    override fun onNext(t: TimeDate) {
                        callBack.loadHistoryDate(t)
                    }

                    override fun onSubscribe(d: Disposable) {

                    }


                    override fun onError(e: Throwable) {
                        callBack.onDataNotAvailable()

                    }

                    override fun onComplete() {

                    }
                })


    }

    override fun getGankDayData(year: String, month: String, day: String, callBack: HomeSource.GankDayLoadCallbak) {
        RetrofitHelper().getGankApi(Constract().gankBaseUrl)
                .getGankDayData(year, month, day)
                .subscribeOn(Schedulers.io())
                .map { addData(it.results!!) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<List<Meizi>> {
                    override fun onNext(t: List<Meizi>) {
                        callBack.loadData(t as ArrayList<Meizi>)
                    }

                    override fun onSubscribe(d: Disposable) {

                    }


                    override fun onError(e: Throwable) {
                        callBack.onDataNotAvailable()

                    }

                    override fun onComplete() {

                    }
                })

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



