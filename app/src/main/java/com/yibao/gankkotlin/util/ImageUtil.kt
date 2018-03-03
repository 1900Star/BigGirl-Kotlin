package com.yibao.gankkotlin.util

import android.content.Context
import android.media.MediaScannerConnection
import android.os.Environment
import android.view.ViewGroup
import android.widget.ImageView
import com.yibao.gankkotlin.MyApplication
import com.yibao.gankkotlin.model.DownGrilProgressData
import com.yibao.gankkotlin.vp.view.ZoomImageView
import io.reactivex.Observable
import io.reactivex.Observable.create
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


/**
 *  @项目名：  MyKotlin
 *  @包名：    com.yibao.gankkotlin.util
 *  @文件名:   ImageUtil
 *  @author:   Stran
 *  @Email:   www.strangermy@outlook.com / www.stranger98@gmail.com
 *  @创建时间:  2018/1/14 17:50
 *  @描述：    {保存图片}
 */
class ImageUtil {
    private lateinit var name: String
    private lateinit var file: File


    fun savaPic(context: Context, url: String): Observable<Int> {
        return create({
            name = getNameFromUrl(url)

            val appDir = File(Environment.getExternalStorageDirectory().absolutePath + "/girls_kotlin")
            if (!appDir.exists()) {
                appDir.mkdir()
            }
            file = File(appDir.absolutePath, name)

            if (!file.exists()) {
                try {
                    file.createNewFile()

                } catch (e: IOException) {
                    e.printStackTrace()
                    it.onError(e)
                }
                val request = Request.Builder().url(url).addHeader("Accept-Encoding", "identity")
                        .build()
                MyApplication().getOkhttpClient()
                        .newCall(request)
                        .enqueue(object : Callback {
                            override fun onFailure(call: Call, e: IOException) {
                                e.printStackTrace()
                                it.onNext(Constract().DWON_PIC_EROOR)
                                it.onError(e)
                                println("下载出错 " + e.toString())
                            }

                            override fun onResponse(call: Call, response: Response) {
                                val inputStream = response.body()!!.byteStream()
                                val buffer = ByteArray(1024 * 4)
                                var fos: FileOutputStream? = null
                                val total = response.body()!!.contentLength()
                                var sum: Long = 0
                                var len = 0
                                val off = 0
                                try {
                                    fos = FileOutputStream(file)
                                    while (inputStream.read(buffer).apply { len = this } > 0) {
                                        fos.write(buffer, off, len)
                                        sum += len.toLong()
                                        val progress = (sum * 1.0f / total * 100).toInt()
                                        //  Rxbus发送下载进度
                                        RxBus.post(DownGrilProgressData(progress))
                                    }
                                    fos.flush()
                                    fos.close()
                                } catch (e: IOException) {
                                    e.printStackTrace()
                                    it.onNext(Constract().DWON_PIC_EROOR)
                                    it.onError(e)
                                } finally {
                                    try {
                                        fos!!.close()
                                    } catch (e: IOException) {
                                        e.printStackTrace()
                                    }
                                }
                            }
                        })
                it.onNext(Constract().FIRST_DWON)
                it.onComplete()
                MediaScannerConnection.scanFile(context, arrayOf(file.absolutePath), null, null)
            } else {
                it.onNext(Constract().EXISTS)
                it.onComplete()
            }

        })

    }


    fun creatZoomView(context: Context): ZoomImageView {
        val view = ZoomImageView(context)
        val params = ViewGroup.LayoutParams(1080, 1920)
        view.scaleType = ImageView.ScaleType.MATRIX
        view.reSetState()
        view.layoutParams = params
        return view
    }

    private fun getNameFromUrl(url: String): String =
            url.substring(url.lastIndexOf("/") + 1, url.length)

}

