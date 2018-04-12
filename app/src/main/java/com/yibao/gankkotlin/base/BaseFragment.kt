package com.yibao.gankkotlin.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yibao.gankkotlin.util.NetworkUtil


/**
 *  @项目名：  MyKotlin
 *  @包名：    com.yibao.mykotlin.base
 *  @文件名:   BaseFragment
 *  @author:   Stran
 *  @Email:   www.strangermy@outlook.com / www.stranger98@gmail.com
 *  @创建时间:  2018/1/12 14:52
 *  @描述：    {TODO}
 */
abstract class BaseFragment : Fragment() {
    protected var mContentView: View? = null

    private var mIsLoadedData = false


    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        val networkConnected = NetworkUtil().isNetworkConnected(activity)
        if (isResumed) {
            viewStatusProcessing(isVisibleToUser)
        }
    }


    /**
     * 处理对用户是否可见
     *
     * @param isVisibleToUser
     */
    private fun viewStatusProcessing(isVisibleToUser: Boolean) {
        if (isVisibleToUser) {
            // 对用户可见
            if (!mIsLoadedData) {
                mIsLoadedData = true
                onLazyLoadData()
            }
            onVisibleToUser()
        } else {
            // 对用户不可见
            onInvisibleToUser()
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // 避免多次从xml中加载布局文件
        if (mContentView == null) {
            initView(savedInstanceState)
            processLogic(savedInstanceState)
        } else {
            val parent = mContentView!!.parent as ViewGroup
            parent.removeView(mContentView)
        }
        return mContentView
    }


    protected fun setContentView(@LayoutRes layoutResID: Int) {
        mContentView = LayoutInflater.from(activity).inflate(layoutResID, null)
    }

    protected open fun onLazyLoadData() {}

    protected open fun onVisibleToUser() {}

    protected fun onInvisibleToUser() {}
    protected abstract fun initView(savedInstanceState: Bundle?)
    protected abstract fun processLogic(savedInstanceState: Bundle?)


}