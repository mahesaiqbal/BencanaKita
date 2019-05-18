package com.mahesaiqbal.bencanakita.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.widget.Toast
import com.mahesaiqbal.bencanakita.R
import com.mahesaiqbal.bencanakita.model.years.Years
import com.mahesaiqbal.bencanakita.ui.adapter.YearsAdapter
import kotlinx.android.synthetic.main.activity_years.*

class YearsActivity : AppCompatActivity() {

    private lateinit var yearsAdapter: YearsAdapter
    private var years: MutableList<Years> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_years)

        initData()
    }

    private fun initData() {
        years.add(Years("2019"))
        years.add(Years("2018"))
        years.add(Years("2017"))
        years.add(Years("2016"))
        years.add(Years("2015"))

        yearsAdapter = YearsAdapter(this, years, { years -> itemClicked(years) })

        list_years.apply {
            layoutManager = LinearLayoutManager(this@YearsActivity)
            adapter = yearsAdapter
        }
    }

    private fun itemClicked(years: Years) {
        val intent = Intent(this, MonthsActivity::class.java)
        intent.putExtra("year", years.year)
        startActivity(intent)
    }
}
