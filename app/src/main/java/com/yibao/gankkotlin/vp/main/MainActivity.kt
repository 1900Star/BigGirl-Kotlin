package com.yibao.gankkotlin.vp.main

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.yibao.gankkotlin.R
import com.yibao.gankkotlin.base.listener.OnLongTouthPreviewListener
import com.yibao.gankkotlin.base.listener.OnTabbarVisibleListener
import com.yibao.gankkotlin.util.GlideUtil
import com.yibao.gankkotlin.vp.home.PreviewGirlDialog
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), OnLongTouthPreviewListener, OnTabbarVisibleListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        initData()
    }

    private fun initData() {
        view_pager_main.offscreenPageLimit = 3
        view_pager_main.adapter = MainPagerAdapter(supportFragmentManager)
    }

    private fun init() {
        setSupportActionBar(toolbar)
        collapsing_toolbar_layout.isTitleEnabled = false
        bottom_gank.setOnNavigationItemSelectedListener(bottomNavigationListener)

    }

    private val bottomNavigationListener = BottomNavigationView.OnNavigationItemSelectedListener(
            function = { item: MenuItem ->
                when (item.itemId) {
                    R.id.navigation_home -> {
                        view_pager_main.setCurrentItem(0, true)
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.navigation_dashboard -> {
                        view_pager_main.setCurrentItem(1, true)
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.navigation_notifications -> {
                        view_pager_main.setCurrentItem(2, true)
                        return@OnNavigationItemSelectedListener true
                    }
                }
                false
            })


    fun setCllospingImage(url: String?) {
        if (url != null) {
            GlideUtil().loadPic(url, iv_collapsing)
        }
    }

    override fun onPreviewGirl(url: String) {
        setCllospingImage(url)
        PreviewGirlDialog().newInstance(url).show(supportFragmentManager, "biggirl")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun showAndHintTabbar(isShowTabbar: Boolean) {
        bottom_gank.visibility=if (isShowTabbar) View.VISIBLE else View.INVISIBLE
    }

}

