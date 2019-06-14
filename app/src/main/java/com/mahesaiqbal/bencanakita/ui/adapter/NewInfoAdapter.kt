package com.mahesaiqbal.bencanakita.ui.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.mahesaiqbal.bencanakita.R
import com.mahesaiqbal.bencanakita.model.newinfo.Data
import com.mahesaiqbal.bencanakita.ui.adapter.NewInfoAdapter.NewInfoViewHolder
import kotlinx.android.synthetic.main.item_new_info.view.*

class NewInfoAdapter(val ctx: Context, private val newInfo: List<Data>, val listener: (Data) -> Unit) :
    RecyclerView.Adapter<NewInfoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NewInfoViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_new_info, parent, false))

    override fun onBindViewHolder(holder: NewInfoViewHolder, position: Int) {
        holder.bindItem(newInfo[position], listener)
    }

    override fun getItemCount(): Int = newInfo.size

    class NewInfoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindItem(data: Data, listener: (Data) -> Unit) {
            itemView.title.text = data.judul
            itemView.setOnClickListener { listener(data) }
        }
    }
}