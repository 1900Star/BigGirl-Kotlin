package com.yibao.gankkotlin.vp.gank.girls

import android.content.Intent
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.yibao.gankkotlin.R
import com.yibao.gankkotlin.base.BaseRvAdapter
import com.yibao.gankkotlin.base.listener.OnLongTouthPreviewListener
import com.yibao.gankkotlin.model.Meizi
import com.yibao.gankkotlin.vp.gank.girl.PicActivity

/**
 * Author：Sid
 * Des：${TODO}
 * Time:2017/10/16 23:23
 */
class GirlsAdapter(val context: FragmentActivity?, private var list: ArrayList<Meizi>) : BaseRvAdapter<Meizi>(context, list) {
    override val layoutId: Int
        get() = R.layout.item_gank_girls

    override fun bindView(holder: RecyclerView.ViewHolder, t: Meizi) {
        if (holder is GirlsViewHolder) {
            holder.setImage(t.url)

            holder.iv.setOnLongClickListener {
                if (context is OnLongTouthPreviewListener) {
                    (context as OnLongTouthPreviewListener).onPreviewGirl(t.url)
                }
                true
            }
            holder.iv.setOnClickListener {
                val intent = Intent(context, PicActivity::class.java)
                intent.putExtra("position", holder.adapterPosition)
                intent.putParcelableArrayListExtra("list", list)
                context?.startActivity(intent)
            }
        }
    }

    override fun getViewHolder(view: View): RecyclerView.ViewHolder = GirlsViewHolder(view)

    override fun getItemCount(): Int = list.size

    class GirlsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var iv: ImageView = itemView.findViewById(R.id.iv_item)
        fun setImage(url: String) {
            Glide.with(iv.context).load(url).asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv)
        }


    }
}


