package com.mahesaiqbal.bencanakita.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mahesaiqbal.bencanakita.R
import com.mahesaiqbal.bencanakita.model.video.Data
import com.mahesaiqbal.bencanakita.ui.adapter.VideoAdapter.VideoViewHolder
import kotlinx.android.synthetic.main.item_video.view.*

class VideoAdapter(val ctx: Context, private val newInfo: List<Data>, val listener: (Data) -> Unit) :
    RecyclerView.Adapter<VideoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VideoViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_video, parent, false))

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bindItem(newInfo[position], listener)
    }

    override fun getItemCount(): Int = newInfo.size

    class VideoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindItem(data: Data, listener: (Data) -> Unit) {
            itemView.title_content.text = data.judul
            itemView.detail_content.text = data.judul_gambar
            Glide.with(itemView.context)
                .load("http://sibenta.hessananda.com/assets/img/video/${data.gambar}")
                .into(itemView.img_content)
            itemView.setOnClickListener { listener(data) }
        }
    }
}
