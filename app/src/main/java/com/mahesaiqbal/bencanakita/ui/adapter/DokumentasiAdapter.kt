package com.mahesaiqbal.bencanakita.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mahesaiqbal.bencanakita.R
import com.mahesaiqbal.bencanakita.model.dokumentasi.Data
import com.mahesaiqbal.bencanakita.ui.adapter.DokumentasiAdapter.DokumentasiViewHolder
import kotlinx.android.synthetic.main.item_dokumentasi.view.*

class DokumentasiAdapter(val ctx: Context, private val dokumentasi: List<Data>, private val listener: (Data) -> Unit) :
    RecyclerView.Adapter<DokumentasiViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DokumentasiViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_dokumentasi, parent, false))

    override fun onBindViewHolder(holder: DokumentasiViewHolder, position: Int) {
        holder.bindItem(dokumentasi[position], listener)
    }

    override fun getItemCount(): Int = dokumentasi.size

    class DokumentasiViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindItem(data: Data, listener: (Data) -> Unit) {
            itemView.title.text = data.judul
            itemView.setOnClickListener { listener(data) }
        }
    }
}