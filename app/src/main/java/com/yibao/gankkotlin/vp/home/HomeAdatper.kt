package com.yibao.gankkotlin.vp.home

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.yibao.gankkotlin.R
import com.yibao.gankkotlin.base.BaseRvAdapter
import com.yibao.gankkotlin.base.listener.OnLongTouthPreviewListener
import com.yibao.gankkotlin.model.Meizi
import com.yibao.gankkotlin.util.Constract
import com.yibao.gankkotlin.vp.web.WebActivity


/**
 *  @项目名：  MyKotlin
 *  @包名：    com.yibao.gankkotlin.vp.home
 *  @文件名:   HomeAdatper
 *  @author:   Stran
 *  @Email:   www.strangermy@outlook.com / www.stranger98@gmail.com
 *  @创建时间:  2018/1/18 0:13
 *  @描述：    {TODO}
 */
class HomeAdatper(context: Context, list: ArrayList<Meizi>) : BaseRvAdapter<Meizi>(context, list) {
    override val layoutId: Int
        get() = R.layout.item_gank_day


    @RequiresApi(Build.VERSION_CODES.N)
    override fun bindView(holder: RecyclerView.ViewHolder, t: Meizi) {
        if (holder is HomeViewHolder) {
            categoryType(holder, t)
            mContext?.let { holder.setData(it, t) }
        }
    }


    private fun categoryType(holder: HomeViewHolder, t: Meizi) {
        val position = holder.adapterPosition
        val lastPosition = position - 1
        if (position == 0) {
            holder.ivGirl.visibility = View.GONE
            showCategroy(holder)
        } else {
            if (position != 0 && t.type == Constract().gankFuli) {
//                GlideUtil().loadPic(t.url, holder.ivGirl)
//                holder.ivGirl.visibility = View.VISIBLE
            }
            if (t.type == mList[lastPosition].type) {
                hintCategory(holder)
            } else {
                showCategroy(holder)
            }
        }


    }

    private fun hintCategory(holder: HomeViewHolder) {
        if (isVisibleCategory(holder.category)) {
            holder.category.visibility = View.GONE
        }
    }

    private fun showCategroy(holder: HomeViewHolder) {
        if (isVisibleCategory(holder.category)) {
            holder.category.visibility = View.VISIBLE
        }
    }

    private fun isVisibleCategory(view: View): Boolean = view.visibility == View.VISIBLE

    override fun getViewHolder(view: View): RecyclerView.ViewHolder = HomeViewHolder(view)


    class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var category: TextView = itemView.findViewById(R.id.category)
        var ivGirl: ImageView = itemView.findViewById(R.id.iv_gank_fuli)
        var content: TextView = itemView.findViewById(R.id.tv_gank_content)
        var gankLayout: LinearLayout = itemView.findViewById(R.id.gank_item_content) as LinearLayout

        @RequiresApi(Build.VERSION_CODES.N)
        fun setData(context: Context, meizi: Meizi) {
            category.text = meizi.type
            val mWho = if ("null" == meizi.who) "1900" else meizi.who
            val des = meizi.desc + "  [" + mWho + "]"
            val createdTime = meizi.createdAt
            val girlDate = createdTime.substring(0, createdTime.lastIndexOf("T"))
            val girl = "$girlDate  有福利-_-点击查看"
            content.text = if (meizi.type == Constract().gankFuli) girl else des
            content.movementMethod = LinkMovementMethod.getInstance()
            content.setOnClickListener {
                if (meizi.type == Constract().gankFuli) {
                    if (context is OnLongTouthPreviewListener) {
                        (context as OnLongTouthPreviewListener).onPreviewGirl(meizi.url)
                    }
                } else {
                    val intent = Intent(gankLayout.context, WebActivity::class.java)
                    intent.putExtra("url", meizi.url)
                    gankLayout.context.startActivity(intent)
                }


            }

        }


    }

}