package com.mahesaiqbal.bencanakita.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mahesaiqbal.bencanakita.R
import com.mahesaiqbal.bencanakita.model.dokumentasifoto.Data
import com.mahesaiqbal.bencanakita.ui.adapter.DokumentasiFotoAdapter.DokumentasiFotoViewHolder
import kotlinx.android.synthetic.main.item_dokumentasi_foto.view.*

class DokumentasiFotoAdapter(val ctx: Context, private val dokumentasiFoto: List<Data>) :
    RecyclerView.Adapter<DokumentasiFotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DokumentasiFotoViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_dokumentasi_foto, parent, false))

    override fun onBindViewHolder(holder: DokumentasiFotoViewHolder, position: Int) {
        holder.bindItem(dokumentasiFoto[position])
    }

    override fun getItemCount(): Int = dokumentasiFoto.size

    class DokumentasiFotoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindItem(data: Data) {
            Glide.with(itemView.context)
                .load("http://sibenta.hessananda.com/assets/img/dokumentasi/${data.nama_file}")
                .into(itemView.img_content)
        }
    }
}