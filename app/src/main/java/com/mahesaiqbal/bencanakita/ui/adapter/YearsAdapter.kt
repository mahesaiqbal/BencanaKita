package com.mahesaiqbal.bencanakita.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mahesaiqbal.bencanakita.R
import com.mahesaiqbal.bencanakita.model.Years
import com.mahesaiqbal.bencanakita.ui.adapter.YearsAdapter.YearsViewHolder
import kotlinx.android.synthetic.main.item_year.view.*

class YearsAdapter(private val ctx: Context, private val years: List<Years>, private val listener: (Years) -> Unit)
    : RecyclerView.Adapter<YearsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        YearsViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_year, parent, false))

    override fun onBindViewHolder(holder: YearsViewHolder, position: Int) {
        holder.bindItem(years[position], listener)
    }

    override fun getItemCount(): Int = years.size

    class YearsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindItem(years: Years, listener: (Years) -> Unit) {
            itemView.year.text = years.year
            itemView.setOnClickListener { listener(years) }
        }
    }
}