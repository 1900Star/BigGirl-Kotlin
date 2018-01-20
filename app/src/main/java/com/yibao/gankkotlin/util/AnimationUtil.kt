package com.yibao.gankkotlin.util

import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.OvershootInterpolator
import android.view.animation.ScaleAnimation


/**
 *  @项目名：  MyKotlin
 *  @包名：    com.yibao.gankkotlin.util
 *  @文件名:   AnimationUtil
 *  @author:   Stran
 *  @Email:   www.strangermy@outlook.com / www.stranger98@gmail.com
 *  @创建时间:  2018/1/19 14:57
 *  @描述：    {TODO}
 */
class AnimationUtil {
    fun applyBobbleAnim(targetView: View) {
        val bobbleAnimSet = AnimationSet(true)
        val expand = ScaleAnimation(0.8f,
                1.0f,
                0.8f,
                1.0f,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f)
        expand.duration = 300

        bobbleAnimSet.addAnimation(expand)
        bobbleAnimSet.fillAfter = true
        bobbleAnimSet.interpolator = OvershootInterpolator()

        targetView.startAnimation(bobbleAnimSet)
    }
}