package com.mahesaiqbal.bencanakita.ui.activity

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.mahesaiqbal.bencanakita.R
import com.mahesaiqbal.bencanakita.model.video.Data
import com.mahesaiqbal.bencanakita.network.InstanceRetrofit
import com.mahesaiqbal.bencanakita.network.Service
import com.mahesaiqbal.bencanakita.ui.adapter.VideoAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_video.*


class VideoActivity : AppCompatActivity() {

    lateinit var service: Service

    var video: ArrayList<Data> = arrayListOf()
    var videoIds: ArrayList<String> = arrayListOf()

    private lateinit var year: String
    private lateinit var month: String
    private lateinit var videoAdapter: VideoAdapter

    private lateinit var progressDialog: ProgressDialog

    private var compositeDisposable: CompositeDisposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        year = intent.getStringExtra("year")
        month = intent.getStringExtra("month")

        compositeDisposable = CompositeDisposable()
        service = InstanceRetrofit.create()

        initData()
    }

    private fun initData() {
        progressDialog = ProgressDialog.show(this, "Loading", "Loading Data...", true, true)
        compositeDisposable?.add(
            service.getVideo(year, month)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.error == false) {
                        video = it.data as ArrayList<Data>
                        setVideoData(video)
                        progressDialog.dismiss()
                    } else {
                        Toast.makeText(this, "Data tidak tersedia pada tahun ${year} bulan ${month}", Toast.LENGTH_SHORT).show()
                        progressDialog.dismiss()
                    }
                }, {
                    Toast.makeText(this, "Data tidak tersedia pada tahun ${year} bulan ${month}", Toast.LENGTH_SHORT).show()
                    progressDialog.dismiss()
                }
            )
        )
    }

    private fun setVideoData(video: ArrayList<Data>) {
        videoAdapter = VideoAdapter(this, video, { data -> itemClicked(data) })

        list_data.apply {
            layoutManager = LinearLayoutManager(this@VideoActivity)
            adapter = videoAdapter
        }
    }

    private fun itemClicked(data: Data) {
        val intent = Intent(this, VideoDetailActivity::class.java)
        intent.putExtra("link_video", data.link_video)
        startActivity(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.clear()
    }
}
