package com.mahesaiqbal.bencanakita.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.mahesaiqbal.bencanakita.R
import com.mahesaiqbal.bencanakita.model.NewInfo.Data
import com.mahesaiqbal.bencanakita.ui.adapter.NewInfoAdapter.NewInfoViewHolder
import kotlinx.android.synthetic.main.item_new_info.view.*

class NewInfoAdapter(val ctx: Context, private val newInfo: List<Data>) :
    RecyclerView.Adapter<NewInfoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NewInfoViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_new_info, parent, false))

    override fun onBindViewHolder(holder: NewInfoViewHolder, position: Int) {
        holder.bindItem(newInfo[position])
    }

    override fun getItemCount(): Int = newInfo.size

    class NewInfoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindItem(data: Data) {
            itemView.title_content.text = data.judul
            itemView.detail_content.text = data.konten
            Glide.with(itemView.context)
                .load("http://sibenta.hessananda.com/assets/img/informasi/${data.gambar}")
                .into(itemView.img_content)
        }
    }
}