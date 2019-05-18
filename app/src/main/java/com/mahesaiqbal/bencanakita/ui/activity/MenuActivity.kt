package com.mahesaiqbal.bencanakita.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.mahesaiqbal.bencanakita.R
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {

    private lateinit var year: String
    private lateinit var month: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        year = intent.getStringExtra("year")
        month = intent.getStringExtra("month")

        new_info.setOnClickListener {
            val intent = Intent(this, NewInfoActivity::class.java)
            intent.putExtra("year", year)
            intent.putExtra("month", month)
            startActivity(intent)
        }

        documentation.setOnClickListener {
            Toast.makeText(this, "Documentation", Toast.LENGTH_SHORT).show()
        }

        video.setOnClickListener {
            Toast.makeText(this, "Video", Toast.LENGTH_SHORT).show()
        }

        map.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            intent.putExtra("year", year)
            intent.putExtra("month", month)
            startActivity(intent)
        }
    }
}
