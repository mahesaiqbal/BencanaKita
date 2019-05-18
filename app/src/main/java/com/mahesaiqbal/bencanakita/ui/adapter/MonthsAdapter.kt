package com.mahesaiqbal.bencanakita.ui.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mahesaiqbal.bencanakita.R
import com.mahesaiqbal.bencanakita.model.months.Months
import com.mahesaiqbal.bencanakita.ui.adapter.MonthsAdapter.MonthsViewHolder
import kotlinx.android.synthetic.main.item_month.view.*

class MonthsAdapter(private val ctx: Context, private val months: List<Months>, private val listener: (Months) -> Unit)
    : RecyclerView.Adapter<MonthsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MonthsViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_month, parent, false))

    override fun onBindViewHolder(holder: MonthsViewHolder, position: Int) {
        holder.bindItem(months[position], listener)
    }

    override fun getItemCount(): Int = months.size

    class MonthsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindItem(months: Months, listener: (Months) -> Unit) {
            itemView.month.text = months.month
            itemView.setOnClickListener { listener(months) }
        }
    }
}