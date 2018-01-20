package com.yibao.gankkotlin.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.view.ViewGroup
import android.widget.ImageView
import com.yibao.gankkotlin.MyApplication
import com.yibao.gankkotlin.model.DownGrilProgressData
import com.yibao.gankkotlin.vp.view.ZoomImageView
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*


/**
 *  @项目名：  MyKotlin
 *  @包名：    com.yibao.gankkotlin.util
 *  @文件名:   ImageUtil
 *  @author:   Stran
 *  @Email:   www.strangermy@outlook.com / www.stranger98@gmail.com
 *  @创建时间:  2018/1/14 17:50
 *  @描述：    {TODO}
 */
class ImageUtil {
    private lateinit var name: String
    private lateinit var file: File


    /**
     * 保存图片
     */
    fun downloadPic(url: String, type: Int): Int {
        name = if (type == 1) {
            "share_y.jpg"
        } else {
            getNameFromUrl(url)
//            randomUUID() + ".jpg"
        }

        val appDir = File(Environment.getExternalStorageDirectory().absolutePath + "/girls")
//        val appDir = (Environment.getExternalStorageDirectory(),"/girls")
        if (!appDir.exists()) {
            appDir.mkdir()
        }
        file = File(appDir.absolutePath, name)
        println("FilePath ===   " + file.absolutePath)

        if (!file.exists()) {
            try {
                file.createNewFile()

            } catch (e: IOException) {
                e.printStackTrace()
                return Constract().DWON_PIC_EROOR
            }

        } else {
            return Constract().EXISTS
        }

        val request = Request.Builder().url(url).addHeader("Accept-Encoding", "identity")
                .build()
        MyApplication().getOkhttpClient()
                .newCall(request)
                .enqueue(object : Callback {
                    override fun onFailure(call: okhttp3.Call, e: IOException) {
                        e.printStackTrace()
                        println("下载出错 " + e.toString())
                    }

                    override fun onResponse(call: okhttp3.Call, response: Response) {
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
                                RxBus.post(DownGrilProgressData(progress, type))
                            }
                            fos.flush()
                            fos.close()
                        } catch (e: IOException) {
                            e.printStackTrace()

                        } finally {
                            try {
                                fos!!.close()
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }

                        }


                    }


                })
        return Constract().FIRST_DWON

    }

    //将下载的图片更新到图库
    fun insertImageToPhoto(): Boolean {

        try {
            MediaStore.Images.Media.insertImage(MyApplication().getInstance()
                    .contentResolver,
                    file.absolutePath,
                    name,
                    null)
            MyApplication().getInstance()
                    .sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                            Uri.parse("file://" + file)))
        } catch (e: Exception) {
            LogUtil().d("图片保存出错 ！ ", e.toString())
            e.printStackTrace()
            return false
        }

        return true
    }

    fun randomUUID(): String {
        return UUID.randomUUID().toString().replace("-", "")
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

