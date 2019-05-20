package com.mahesaiqbal.bencanakita.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.mahesaiqbal.bencanakita.R
import kotlinx.android.synthetic.main.activity_map_detail.*

class MapDetailActivity : AppCompatActivity() {

    private lateinit var img: String
    private lateinit var title: String
    private lateinit var desc: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_detail)

        img = intent.getStringExtra("img")
        title = intent.getStringExtra("title")
        desc = intent.getStringExtra("desc")

        initView()
    }

    private fun initView() {
        title_content.text = title
        detail_content.text = desc
        Glide.with(this).load(img).into(img_content)
    }
}
