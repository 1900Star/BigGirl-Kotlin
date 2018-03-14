package com.yibao.gankkotlin.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.yibao.gankkotlin.R


/**
 *  @项目名：  MyKotlin
 *  @包名：    com.yibao.gankkotlin.util
 *  @文件名:   GlideUtil
 *  @author:   Stran
 *  @Email:   www.strangermy@outlook.com / www.stranger98@gmail.com
 *  @创建时间:  2018/1/14 15:08
 *  @描述：    {TODO}
 */
class GlideUtil {
    fun loadPic(url: String, image: ImageView) {
        Glide.with(image.context).load(url).asBitmap()
                .placeholder(R.mipmap.star)
                .error(R.mipmap.star)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(image)

    }

    fun loadPics(url: String, image: ImageView) {
        Glide.with(image.context).load(url).asBitmap()
                .error(R.mipmap.star)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(image)

    }

}