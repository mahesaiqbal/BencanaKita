package com.mahesaiqbal.bencanakita.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
        compositeDisposable?.add(
            service.getVideo(year, month)
                .subscribeOn(Schedulers.io())
                .observeOn(
                    AndroidSchedulers.mainThread()
                ).subscribe({
                    video = it.data as ArrayList<Data>
                    setVideoData(video)
                }, {

                })
        )
    }

    private fun setVideoData(video: ArrayList<Data>) {
        for (i in video) {
            videoIds.add(i.link_video.subSequence(32, 43).toString())
        }

        videoAdapter = VideoAdapter(videoIds, this.lifecycle)

        list_data.apply {
            layoutManager = LinearLayoutManager(this@VideoActivity)
            adapter = videoAdapter
        }
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
