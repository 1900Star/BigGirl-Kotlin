package com.yibao.gankkotlin.model

import com.yibao.gankkotlin.network.RetrofitHelper
import com.yibao.gankkotlin.util.Constract
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
class GankGenericeRemote : GankGenericeSource {


    override fun getGankGenericeData(page: Int, size: Int, loadType: String, callBack: GankGenericeSource.GankGenericeLoadCallbak) {
        RetrofitHelper().getGankApi(Constract().gankBaseUrl)
                .getGril(loadType, size, page)
                .subscribeOn(Schedulers.io())
                .map { it.results }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { callBack.loadData(it) }


    }



}



