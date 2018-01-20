package com.yibao.gankkotlin.vp.web

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.webkit.GeolocationPermissions
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.yibao.gankkotlin.R
import kotlinx.android.synthetic.main.activity_web_view.*


/**
 *  @项目名：  MyKotlin
 *  @包名：    com.yibao.gankkotlin.vp.web
 *  @文件名:   WebActivity
 *  @author:   Stran
 *  @Email:   www.strangermy@outlook.com / www.stranger98@gmail.com
 *  @创建时间:  2018/1/14 14:06
 *  @描述：    {TODO}
 */
class WebActivity : AppCompatActivity() {
    private val httpFlag = "http"
    private val httpsFlag = "https"
    private lateinit var mUrl: String
    private lateinit var mWebView: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        mUrl = intent.getStringExtra("url")

        initView()

        initData()
    }

    private fun initView() {
        toolbar.inflateMenu(R.menu.activity_web_normal)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
        toolbar.setNavigationOnClickListener { finish() }
        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                android.R.id.home -> finish()
            // 分享
                R.id.web_normal_share -> {
                    val shareIntent = Intent(Intent.ACTION_SEND)
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, title)
                    shareIntent.putExtra(Intent.EXTRA_TEXT, mUrl)
                    shareIntent.type = "text/plain"
                    startActivity(shareIntent)
                }
            // 浏览器打开
                R.id.web_normal_browser -> {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(mUrl)
                    startActivity(intent)
                }
                else -> {
                }
            }
            false
        }
    }


    @SuppressLint("SetJavaScriptEnabled")
    private fun initData() {
        val client = object : WebViewClient() {


            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                return if (url.startsWith(httpFlag) || url.startsWith(httpsFlag)) {
                    view.loadUrl(url)
                    false
                    //
                } else {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                    true
                }
            }

        }


        val chromeClient = object : WebChromeClient() {

            override fun onProgressChanged(view: WebView, newProgress: Int) {
                if (newProgress < 100) {

                    progress_bar_web.visibility = View.VISIBLE
                    progress_bar_web.progress = newProgress

                } else if (newProgress == 100) {
                    progress_bar_web.visibility = View.GONE
                }


            }

            override fun onReceivedTitle(view: WebView, title: String) {
                super.onReceivedTitle(view, title)
                toolbar.title = title

            }

            override fun onGeolocationPermissionsShowPrompt(origin: String,
                                                            callback: GeolocationPermissions.Callback) {
                callback.invoke(origin, false, false)
                super.onGeolocationPermissionsShowPrompt(origin, callback)
            }

            override fun onCreateWindow(view: WebView,
                                        isDialog: Boolean,
                                        isUserGesture: Boolean,
                                        resultMsg: Message): Boolean {
                val transport = resultMsg.obj as WebView.WebViewTransport
                transport.webView = view
                resultMsg.sendToTarget()

                return true
            }

        }

        mWebView = WebView(this.applicationContext)
        mWebView.layoutParams = ViewGroup.LayoutParams(ViewGroup.MarginLayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)
        content_web.addView(mWebView)
        val settings = mWebView.settings
        // 基本设置
        settings.setSupportZoom(true)
        settings.loadWithOverviewMode = true
        settings.useWideViewPort = true
        settings.defaultTextEncodingName = "utf-8"
        settings.loadsImagesAutomatically = true
        settings.javaScriptEnabled = true
        // 缓存数据
        settings.domStorageEnabled = true
        settings.databaseEnabled = true
        settings.setAppCacheEnabled(true)
        val appCachePath = applicationContext.cacheDir
                .absolutePath
        settings.setAppCachePath(appCachePath)

        //html中的_bank标签就是新建窗口打开，有时会打不开，需要加以下
        //然后 复写 WebChromeClient的onCreateWindow方法
        settings.setSupportMultipleWindows(false)
        settings.javaScriptCanOpenWindowsAutomatically = true
        mWebView.webChromeClient = chromeClient
        mWebView.webViewClient = client
        mWebView.loadUrl(mUrl)

    }

    // 防止内存泄漏

    private fun clearWebViewResource() {
        mWebView.clearHistory()
        (mWebView.parent as ViewGroup).removeView(mWebView)
        mWebView.tag = null
        mWebView.loadUrl("about:blank")
        mWebView.stopLoading()
        mWebView.webViewClient = null
        mWebView.webChromeClient = null
        mWebView.removeAllViews()
        mWebView.destroy()
//        mWebView = null
    }

    public override fun onPause() {
        super.onPause()
        mWebView.onPause()
    }

    public override fun onResume() {
        super.onResume()
        mWebView.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        clearWebViewResource()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
            mWebView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }


}