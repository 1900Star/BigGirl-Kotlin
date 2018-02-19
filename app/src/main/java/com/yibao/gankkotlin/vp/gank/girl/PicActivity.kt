package com.yibao.gankkotlin.vp.gank.girl

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.Menu
import android.view.MenuItem
import com.yibao.gankkotlin.R
import com.yibao.gankkotlin.base.ToolbarActivity
import com.yibao.gankkotlin.model.DownGrilProgressData
import com.yibao.gankkotlin.model.Meizi
import com.yibao.gankkotlin.util.*
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_pic.*
import java.util.concurrent.TimeUnit

class PicActivity : ToolbarActivity(), GirlPagerAdapter.OnGirlClickListener, ViewPager.OnPageChangeListener {


    private lateinit var adapter: GirlPagerAdapter
    private lateinit var pagerScroller: PagerScroller
    private lateinit var mDisposable: Disposable
    private lateinit var list: ArrayList<Meizi>
    private lateinit var mUrl: String
    private var position: Int = 0
    private lateinit var menuAutoPlayItem: MenuItem
    private var isAutoPreview = false
    //默认下载进度
    private val defultDownProgress = 0
    //下载进度最大值
    private var maxDownProgress = 100

    override fun initView(savedInstanceState: Bundle?) {
        list = intent.getParcelableArrayListExtra<Meizi>("list")
        position = intent.getIntExtra("position", 0)
        mUrl = list[position].url
        getProgress()

    }

    override val layoutId: Int get() = R.layout.activity_pic

    override fun initData() {
        pagerScroller = PagerScroller(this)
        adapter = GirlPagerAdapter(this, list)
        vp_pic.adapter = adapter
        vp_pic.currentItem = position
    }

    override fun initListener() {
        vp_pic.addOnPageChangeListener(this)
        iv_down.setOnClickListener {
            saveGirl()
        }

    }

    private fun getProgress() {
        RxBus.toFlowable(DownGrilProgressData::class.java).subscribe {
            iv_down.setProgress(it.progress)
            when (it.progress == maxDownProgress) {
                true -> SnakbarUtil().showSuccessView(vp_pic)
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.girl_menu, menu)
        menuAutoPlayItem = menu.findItem(R.id.action_auto_play)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            android.R.id.home -> finish()
            R.id.action_auto_play -> autoPreview()
        }
        return super.onOptionsItemSelected(item)
    }


    private fun autoPreview() {
        isAutoPreview = if (isAutoPreview) {
            menuAutoPlayItem.setIcon(R.drawable.btn_playing_play)
            //停止自动播放
            stopAutoPlay()
            false
        } else {
            menuAutoPlayItem.setIcon(R.drawable.btn_playing_pause)
            startAutoPlay()
            true
        }

    }

    private fun startAutoPlay() {
        hintToolbar()
        pagerScroller.initViewPagerScroll(vp_pic, 2000)
        mDisposable = Observable.interval(2000, 3000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    var item = vp_pic.currentItem
                    if (item == adapter.count) {
                        vp_pic.currentItem = 0
                    }
                    vp_pic.setCurrentItem(++item, true)
                }
    }

    private fun stopAutoPlay() {
        if (!mDisposable.isDisposed) {
            mDisposable.dispose()
        }
        pagerScroller.duration = 600


    }


    private fun saveGirl() {
        // 网络检查
        val isConnected = NetworkUtil().isNetworkConnected(this)
        if (isConnected) {
            ImageUtil().savaPic(this, mUrl).subscribe {
                when (it) {
                    Constract().EXISTS -> SnakbarUtil().picAlreadyExists(vp_pic)
                    Constract().DWON_PIC_EROOR -> SnakbarUtil().showDownPicFail(vp_pic)
                }
            }
        } else {
            SnakbarUtil().netErrors(vp_pic)
        }
    }

    override fun hintToolbar() {
        hideOrShowToolbar()
        if (mIsHidden) {
            iv_down.visibility = android.view.View.INVISIBLE
        } else {
            iv_down.visibility = android.view.View.VISIBLE
        }

    }

    /**
     * viewpager滚动监听，
     */
    override fun onPageScrollStateChanged(state: Int) {
        if (state < maxDownProgress) {
            // 恢复默认进度
            iv_down.setProgress(defultDownProgress)
        }
    }

    override fun onPageSelected(position: Int) {
        mUrl = list[position].url
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}


    public override fun onPause() {
        super.onPause()
        menuAutoPlayItem.setIcon(R.drawable.btn_playing_play)
        pagerScroller.duration = 300

    }

}
