package com.yibao.gankkotlin.vp.gank.app

import android.os.Bundle
import com.yibao.gankkotlin.base.BaseRvAdapter
import com.yibao.gankkotlin.base.BaseRvFragment
import com.yibao.gankkotlin.model.Meizi
import com.yibao.gankkotlin.util.Constract


/**
 *  @项目名：  MyKotlin
 *  @包名：    com.yibao.mykotlin.vp.gank.app
 *  @文件名:   GankGenericFragment
 *  @author:   Stran
 *  @Email:   www.strangermy@outlook.com / www.stranger98@gmail.com
 *  @创建时间:  2018/1/12 17:20
 *  @描述：    {TODO}
 */
class GankGenericFragment : BaseRvFragment(), GankGenericeContract.Veiw {

    private var mPresenter = GankGenericePresenter(this)
    private lateinit var mAdapter: BaseRvAdapter<Meizi>
    override fun processLogic(savedInstanceState: Bundle?) {
        val position = arguments.getInt("position")
        mLoadType = Constract().getLoadType(position)!!
    }

    override fun onLazyLoadData() {
        super.onLazyLoadData()
        mPresenter.start(this.mLoadType, Constract().loadDataStatus)
    }

    override fun loadData(list: ArrayList<Meizi>) {
        mAdapter = GankGenericeAdapter(activity, list)
        val recyclerView = getRecyclerView( 1, mAdapter)
        mFagContent.addView(recyclerView)
        mSwipeRefresh.isRefreshing = false
    }

    override fun loadMoreData(list: ArrayList<Meizi>) {
        mAdapter.AddFooter(list)
        mAdapter.notifyDataSetChanged()
    }

    override fun refreshData(list: ArrayList<Meizi>) {
        mAdapter.clear()
        mAdapter.AddHeader(list)
        mAdapter.notifyDataSetChanged()

    }

    override fun loadError() {
    }

    override fun loadNormal() {
    }


    override fun refreshData() {
        mPresenter.loadData(size, page, mLoadType, Constract().refreshDataStatus)
    }

    override fun loadMoreData() {
        mPresenter.loadData(size, page, this.mLoadType, Constract().loadDataMore)
    }

    override fun setPresenter(presenter: GankGenericeContract.Presenter) {
        mPresenter = presenter as GankGenericePresenter
    }

    fun newInstance(position: Int): GankGenericFragment {
        val fragment = GankGenericFragment()
        val bundle = Bundle()
        bundle.putInt("position", position)
        fragment.arguments = bundle
        return fragment
    }

}