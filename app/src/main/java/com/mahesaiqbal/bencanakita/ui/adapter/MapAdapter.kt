package com.mahesaiqbal.bencanakita.ui.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.mahesaiqbal.bencanakita.R
import com.mahesaiqbal.bencanakita.model.map.Data
import com.mahesaiqbal.bencanakita.ui.adapter.MapAdapter.MapViewHolder
import kotlinx.android.synthetic.main.item_map.view.*

class MapAdapter(val ctx: Context, private val map: List<Data>, val listener: (Data) -> Unit) :
    RecyclerView.Adapter<MapViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MapViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_map, parent, false))

    override fun onBindViewHolder(holder: MapViewHolder, position: Int) {
        holder.bindItem(map[position], listener)
    }

    override fun getItemCount(): Int = map.size

    class MapViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindItem(data: Data, listener: (Data) -> Unit) {
            itemView.title_content.text = data.judul
            itemView.detail_content.text = data.konten
            Glide.with(itemView.context)
                .load("http://sibenta.hessananda.com/assets/img/peta_terdampak/${data.gambar}")
                .into(itemView.img_content)
            itemView.setOnClickListener { listener(data) }
        }
    }
}