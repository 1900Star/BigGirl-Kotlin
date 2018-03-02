package com.yibao.gankkotlin.vp.home

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.LinearLayout
import com.yibao.gankkotlin.R
import com.yibao.gankkotlin.util.AnimationUtil
import com.yibao.gankkotlin.util.GlideUtil
import com.yibao.gankkotlin.util.ImageUtil


/**
 *  @项目名：  MyKotlin
 *  @包名：    com.yibao.gankkotlin.vp.home
 *  @文件名:   PreviewGirlDialog
 *  @author:   Stran
 *  @Email:   www.strangermy@outlook.com / www.stranger98@gmail.com
 *  @创建时间:  2018/1/19 14:42
 *  @描述：    {TODO}
 */
class PreviewGirlDialog : DialogFragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!
                .setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val view = LinearLayout.inflate(activity, R.layout.preview_girl_dialog, null)
        initView(view)
        return view
    }

    private fun initView(view: View) {
        val contentView = view.findViewById(R.id.preview_girl_content) as LinearLayout
        val url = arguments.getString("url")
        val image = ImageUtil().creatZoomView(activity)
        GlideUtil().loadPic(url, image)
        image.setOnClickListener { this.dismiss() }
        AnimationUtil().applyBobbleAnim(image)
        contentView.addView(image)
    }

    fun newInstance(url: String): PreviewGirlDialog {
        val fragment = PreviewGirlDialog()
        val bundle = Bundle()
        bundle.putString("url", url)
        fragment.arguments = bundle
        return fragment
    }

}