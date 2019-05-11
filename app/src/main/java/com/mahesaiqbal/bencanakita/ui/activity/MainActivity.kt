package com.mahesaiqbal.bencanakita.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.mahesaiqbal.bencanakita.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        started_btn.setOnClickListener {
            startActivity(Intent(this@MainActivity, HomeActivity::class.java))
        }
    }
}
