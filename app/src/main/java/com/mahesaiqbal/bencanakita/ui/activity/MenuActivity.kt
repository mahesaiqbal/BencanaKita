package com.mahesaiqbal.bencanakita.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mahesaiqbal.bencanakita.R
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        new_info.setOnClickListener {
            Toast.makeText(this, "New Info", Toast.LENGTH_SHORT).show()
        }

        documentation.setOnClickListener {
            Toast.makeText(this, "Documentation", Toast.LENGTH_SHORT).show()
        }

        video.setOnClickListener {
            Toast.makeText(this, "Video", Toast.LENGTH_SHORT).show()
        }

        map.setOnClickListener {
            Toast.makeText(this, "Map", Toast.LENGTH_SHORT).show()
        }
    }
}
