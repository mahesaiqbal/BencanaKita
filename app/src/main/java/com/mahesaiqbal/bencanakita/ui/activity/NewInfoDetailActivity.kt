package com.mahesaiqbal.bencanakita.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mahesaiqbal.bencanakita.R
import kotlinx.android.synthetic.main.activity_new_info_detail.*
import android.os.Environment
import java.io.File
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri

class NewInfoDetailActivity : AppCompatActivity() {

    private lateinit var urlPdf: String
    private lateinit var pdf: String
    private lateinit var title: String
    private lateinit var desc: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_info_detail)

        urlPdf = intent.getStringExtra("url_pdf")
        pdf = intent.getStringExtra("pdf")
        title = intent.getStringExtra("title")
        desc = intent.getStringExtra("desc")

        initView()
    }

    private fun initView() {
        title_content.text = title
        detail_content.text = desc
        img_content.setOnClickListener { v ->
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(Uri.parse(urlPdf), "text/html")
            startActivity(intent)
        }
    }
}
