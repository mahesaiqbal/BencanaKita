package com.mahesaiqbal.bencanakita.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.mahesaiqbal.bencanakita.R
import com.mahesaiqbal.bencanakita.model.Months.Months
import com.mahesaiqbal.bencanakita.ui.adapter.MonthsAdapter
import kotlinx.android.synthetic.main.activity_months.*

class MonthsActivity : AppCompatActivity() {

    private lateinit var monthsAdapter: MonthsAdapter
    private var months: MutableList<Months> = mutableListOf()

    private lateinit var year: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_months)

        year = intent.getStringExtra("year")

        initData()
    }

    private fun initData() {
        months.add(Months("JANUARY", "01"))
        months.add(Months("FEBRUARY", "02"))
        months.add(Months("MARCH", "03"))
        months.add(Months("APRIL", "04"))
        months.add(Months("MAY", "05"))
        months.add(Months("JUNE", "06"))
        months.add(Months("JULY", "07"))
        months.add(Months("AUGUST", "08"))
        months.add(Months("SEPTEMBER", "09"))
        months.add(Months("OCTOBER", "10"))
        months.add(Months("NOVEMBER", "11"))
        months.add(Months("DECEMBER", "12"))

        monthsAdapter = MonthsAdapter(this, months, { months -> itemClicked(months) })

        list_months.apply {
            layoutManager = LinearLayoutManager(this@MonthsActivity)
            adapter = monthsAdapter
        }
    }

    private fun itemClicked(months: Months) {
        Toast.makeText(this, months.month, Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MenuActivity::class.java)
        intent.putExtra("year", year)
        intent.putExtra("month", months.numberOfMonth)
        startActivity(intent)
    }
}
