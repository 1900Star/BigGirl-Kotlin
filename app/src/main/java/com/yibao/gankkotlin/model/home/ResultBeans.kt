package com.yibao.gankkotlin.model.home

import android.os.Parcel
import android.os.Parcelable
import com.yibao.gankkotlin.model.Meizi


/**
 *  @项目名：  MyKotlin
 *  @包名：    com.yibao.gankkotlin.model.dayli
 *  @文件名:   ResultBeans
 *  @author:   Stran
 *  @Email:   www.strangermy@outlook.com / www.stranger98@gmail.com
 *  @创建时间:  2018/1/17 20:08
 *  @描述：    {TODO}
 *  "Android",
"休息视频",
"瞎推荐",
"前端",
"拓展资源",
"iOS",
"福利"
 */


data class ResultBeans(
        val 福利: List<Meizi>?,
        val Android: List<Meizi>?,
        val iOS: List<Meizi>?,
        val App: List<Meizi>?,
        val 前端: List<Meizi>?,
        val 瞎推荐: List<Meizi>?,
        val 休息视频: List<Meizi>?,
        val 拓展资源: List<Meizi>?

) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.createTypedArrayList(Meizi),
            parcel.createTypedArrayList(Meizi),
            parcel.createTypedArrayList(Meizi),
            parcel.createTypedArrayList(Meizi),
            parcel.createTypedArrayList(Meizi),
            parcel.createTypedArrayList(Meizi),
            parcel.createTypedArrayList(Meizi),
            parcel.createTypedArrayList(Meizi))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(福利)
        parcel.writeTypedList(Android)
        parcel.writeTypedList(iOS)
        parcel.writeTypedList(App)
        parcel.writeTypedList(前端)
        parcel.writeTypedList(瞎推荐)
        parcel.writeTypedList(休息视频)
        parcel.writeTypedList(拓展资源)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<ResultBeans> {
        override fun createFromParcel(parcel: Parcel): ResultBeans = ResultBeans(parcel)

        override fun newArray(size: Int): Array<ResultBeans?> = arrayOfNulls(size)
    }
}