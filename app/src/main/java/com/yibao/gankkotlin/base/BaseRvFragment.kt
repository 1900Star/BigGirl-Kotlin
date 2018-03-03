package com.yibao.gankkotlin.base

import android.graphics.Color
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import com.yibao.gankkotlin.R
import com.yibao.gankkotlin.factroy.RecyclerFactory
import com.yibao.gankkotlin.util.Constract
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit


/**
 *  @项目名：  MyKotlin
 *  @包名：    com.yibao.mykotlin.base
 *  @文件名:   BaseRvFragment
 *  @author:   Stran
 *  @Email:   www.strangermy@outlook.com / www.stranger98@gmail.com
 *  @创建时间:  2018/1/12 16:36
 *  @描述：    {TODO}
 */
abstract class BaseRvFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener, BaseView<BasePresenter> {
    var page: Int = 1
    var size: Int = 20
    var mPostition: Int = 0
    var mLoadStatus: Int = Constract().loadDataStatus
    lateinit var mLoadType: String

    lateinit var mSwipeRefresh: SwipeRefreshLayout
    lateinit var mFagContent: LinearLayout
    lateinit var mErrorView: ScrollView
    private lateinit var mTvLoadAgain: TextView

    override fun initView(savedInstanceState: Bundle?) {
        setContentView(R.layout.base_rv_fragment)
        mSwipeRefresh = mContentView?.findViewById<View>(R.id.swipe_refresh) as SwipeRefreshLayout
        mFagContent = mContentView?.findViewById<View>(R.id.fag_content) as LinearLayout
        mErrorView = mContentView?.findViewById<View>(R.id.error_view) as ScrollView
        mTvLoadAgain = mContentView?.findViewById<View>(R.id.tv_again_load) as TextView
        mSwipeRefresh.setColorSchemeColors(Color.BLUE, Color.RED, Color.YELLOW)
        mSwipeRefresh.setOnRefreshListener(this)
        mSwipeRefresh.isRefreshing = true

        mTvLoadAgain.setOnClickListener {
            mErrorView.visibility = View.GONE
            mSwipeRefresh.isRefreshing = true
            againLoadData()
        }

    }

    /**
     * 点击重新加载数据
     */
    abstract fun againLoadData()


    /**
     * 得到一个RecyclerView   实现了加载更多
     * @param rvType
     * @param adapter
     * @return
     */
    fun getRecyclerView(rvType: Int, adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>): RecyclerView {
        val recyclerView = RecyclerFactory().creatRecyclerView(activity, rvType, adapter)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                var lastPosition = -1
                when (newState) {
                    RecyclerView.SCROLL_STATE_IDLE -> {
                        val layoutManager = recyclerView!!.layoutManager
                        //通过LayoutManager找到当前显示的最后的item的position
                        when (layoutManager) {
                            is GridLayoutManager -> lastPosition = layoutManager.findLastVisibleItemPosition()
                            is LinearLayoutManager -> lastPosition = layoutManager.findLastVisibleItemPosition()
                            is StaggeredGridLayoutManager -> {
                                val lastPositions = IntArray(layoutManager.spanCount)
                                layoutManager.findLastVisibleItemPositions(
                                        lastPositions)
                                lastPosition = findMax(lastPositions)
                            }
                        }
                        if (lastPosition == recyclerView.layoutManager
                                        .itemCount - 1) {
                            mLoadStatus = Constract().loadDataMore
                            page++
                            mPostition++
                            loadMoreData()

                        }
                    }

                }
            }

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {

                //得到当前显示的最后一个item的view
                val lastChildView = recyclerView!!.layoutManager.getChildAt(recyclerView.layoutManager.childCount - 1)
                //得到lastChildView的bottom坐标值
                val lastChildBottom = lastChildView.bottom
                //得到Recyclerview的底部坐标减去底部padding值，也就是显示内容最底部的坐标
                val recyclerBottom = recyclerView.bottom - recyclerView.paddingBottom
                //通过这个lastChildView得到这个view当前的position值
                val lastPosition = recyclerView.layoutManager.getPosition(lastChildView)

                //判断lastChildView的bottom值跟recyclerBottom
                //判断lastPosition是不是最后一个position
                //如果两个条件都满足则说明是真正的滑动到了底部,这时候就可以去加载更多了。
                if (lastChildBottom == recyclerBottom && lastPosition == recyclerView.layoutManager.itemCount - 1) {
                    //                    page++;
                    //                    loadMoreData();


                }
            }

        })


        return recyclerView
    }

    /**
     * 下拉刷新
     */
    override fun onRefresh() {
        mErrorView.visibility = View.GONE
        mLoadStatus = Constract().refreshDataStatus
        Observable.timer(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    onRefreshData()

                    mSwipeRefresh.isRefreshing = false
                    page = 1
                }
        mPostition = Constract().loadDataStatus
        mFagContent.visibility = View.VISIBLE
    }

    protected abstract fun onRefreshData()
    //    上拉加载更多
    protected abstract fun loadMoreData()

    override fun loadError() {
        mSwipeRefresh.isRefreshing = false
        mFagContent.visibility = View.INVISIBLE
        mErrorView.visibility = View.VISIBLE
        println("===========展示重新加载视图================")
    }

    override fun loadNormal() {
        mSwipeRefresh.isRefreshing = false
        mFagContent.visibility = View.INVISIBLE
        mErrorView.visibility = View.VISIBLE
        println("===========  没有数据视图================")
    }

    /**
     * 找到数组中的最大值
     */
    fun findMax(lastPositions: IntArray): Int {
        return lastPositions.max()
                ?: lastPositions[0]
    }
}