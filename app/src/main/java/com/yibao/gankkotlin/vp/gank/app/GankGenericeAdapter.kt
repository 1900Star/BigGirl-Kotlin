package com.yibao.gankkotlin.vp.gank.app

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.yibao.gankkotlin.R
import com.yibao.gankkotlin.base.BaseRvAdapter
import com.yibao.gankkotlin.model.Meizi
import com.yibao.gankkotlin.util.GlideUtil
import com.yibao.gankkotlin.vp.web.WebActivity


/**
 *  @项目名：  MyKotlin
 *  @包名：    com.yibao.mykotlin.vp.gank.app
 *  @文件名:   GankGenericeAdapter
 *  @author:   Stran
 *  @Email:   www.strangermy@outlook.com / www.stranger98@gmail.com
 *  @创建时间:  2018/1/12 21:38
 *  @描述：    {TODO}
 */
class GankGenericeAdapter(val context: Context, list: ArrayList<Meizi>) : BaseRvAdapter<Meizi>(context, list) {
    override val layoutId: Int
        get() = R.layout.item_generice_frag


    override fun bindView(holder: RecyclerView.ViewHolder, t: Meizi) {
        if (holder is GankGenericeViewHolder) {
            holder.setData(context, t)
        }

    }

    override fun getViewHolder(view: View): RecyclerView.ViewHolder = GankGenericeViewHolder(view)


    class GankGenericeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var gankRl: RelativeLayout = itemView.findViewById(R.id.item_generice_root)
        private var gankIcon: ImageView = itemView.findViewById(R.id.iv_generice_icon)
        private var gankName: TextView = itemView.findViewById(R.id.tv_generice_name)
        private var gankDes: TextView = itemView.findViewById(R.id.tv_generice_des)
        private var gankTime: TextView = itemView.findViewById(R.id.tv_generice_time)
        private var gankType: TextView = itemView.findViewById(R.id.tv_generice_type)

        fun setData(context: Context, meizi: Meizi) {
            val createdTime = meizi.createdAt
            GlideUtil().loadPic(meizi.url, gankIcon)
            val who = meizi.who
            gankName.text = who ?: "Smartisan"
            gankDes.text = meizi.desc
            gankTime.text = createdTime.substring(0, createdTime.lastIndexOf("T"))
            gankType.text = meizi.type
            gankRl.setOnClickListener {
                val intent = Intent(context, WebActivity::class.java)
                intent.putExtra("url", meizi.url)
                context.startActivity(intent)
            }
        }


    }


}