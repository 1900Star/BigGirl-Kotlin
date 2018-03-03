package com.yibao.gankkotlin.util

/**
 * Author：Sid
 * Des：${TODO}
 * Time:2017/10/20 02:46
 */
class Constract {
    val staarrTitle = arrayOf("Girl", "Android", "App", "iOS", "Video", "前端", "拓展资源")
    val gankBaseUrl: String = "http://gank.io/"
    val gankHistory: String = "history"
    val gankFuli: String = "福利"
    val gakki: String = "http://c.hiphotos.baidu.com/baike/pic/item/91ef76c6a7efce1b27893518a451f3deb58f6546.jpg"

    var loadDataStatus = 0
    var refreshDataStatus = 1
    var loadDataMore = 2
    var noMoreDataStatus = 3
    var loadingData = 4


    private val typeGirls = 0
    private val typeAndroid = 1
    private val typeApp = 2
    private val typeIos = 3
    private val typeVideo = 4
    private val typeFront = 5
    private val typeExtend = 6

    private val fagGirls = "福利"
    private val fagAndroid = "Android"
    private val fagVideo = "休息视频"
    private val fagIos = "iOS"
    private val fagFront = "前端"
    private val fagExpand = "拓展资源"
    private val fagApp = "App"
    private var loadType: String? = null

    fun getLoadType(position: Int?): String? {
        when (position) {
            typeGirls -> loadType = fagGirls
            typeAndroid -> loadType = fagAndroid
            typeIos -> loadType = fagIos
            typeApp -> loadType = fagApp
            typeVideo -> loadType = fagVideo
            typeFront -> loadType = fagFront
            typeExtend -> loadType = fagExpand
        }
        return loadType
    }
    //保存图片f地址

//
//    val dir = FileUtil().getDiskCacheDir(MyApplication().getInstance()) + "/girls"
//
//    val deleteDir = FileUtil().getDiskCacheDir(MyApplication().getInstance()) + "/girls/share_y.jpg"

    //保存图片状态码
    var FIRST_DWON = 0
    var EXISTS = 1
    var DWON_PIC_EROOR = 2

}
