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
import android.widget.TextView
import com.yibao.gankkotlin.R
import com.yibao.gankkotlin.model.DownGrilProgressData
import com.yibao.gankkotlin.util.*
import com.yibao.gankkotlin.vp.view.ProgressBtn
import okhttp3.OkHttpClient


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
    private lateinit var imageUrl: String
    private val maxDownProgress = 100
    private lateinit var progressBtn: ProgressBtn
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!
                .setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val view = LinearLayout.inflate(activity, R.layout.preview_girl_dialog, null)
        initView(view)
        getProgress()
        return view
    }

    private fun initView(view: View) {
        val contentView = view.findViewById(R.id.preview_girl_content) as LinearLayout
        val tvSave = view.findViewById(R.id.tv_save) as TextView
        progressBtn = view.findViewById(R.id.pb_save) as ProgressBtn
        imageUrl = arguments?.getString("url")!!
        val image = activity?.let { ImageUtil().creatZoomView(it) }
        GlideUtil().loadPics(imageUrl, image!!)
        progressBtn.setMax(maxDownProgress)
        tvSave.setOnClickListener { saveGirl() }
        image.setOnClickListener { this.dismiss() }
        AnimationUtil().applyBobbleAnim(image)
        contentView.addView(image)
    }


    private fun getProgress() {
        RxBus.toFlowable(DownGrilProgressData::class.java).subscribe {
            progressBtn.setProgress(it.progress)
            when (it.progress == maxDownProgress) {
                true -> SnakbarUtil().showSuccessView(progressBtn)
            }
        }
    }

    private fun saveGirl() {
        // 网络检查
        val isConnected = NetworkUtil().isNetworkConnected(activity)
        if (isConnected) {
            ImageUtil().savaPic(activity, imageUrl).subscribe {
                when (it) {
                    Constract().EXISTS -> SnakbarUtil().picAlreadyExists(progressBtn)
                    Constract().DWON_PIC_EROOR -> SnakbarUtil().showDownPicFail(progressBtn)
                }
            }
        } else {
            SnakbarUtil().netErrors(progressBtn)
        }
    }


    fun newInstance(url: String): PreviewGirlDialog {
        val fragment = PreviewGirlDialog()
        val bundle = Bundle()
        bundle.putString("url", url)
        fragment.arguments = bundle
        return fragment
    }

}