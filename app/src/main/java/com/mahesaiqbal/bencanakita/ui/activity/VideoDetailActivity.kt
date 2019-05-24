package com.mahesaiqbal.bencanakita.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.mahesaiqbal.bencanakita.R
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.activity_video_detail.*


class VideoDetailActivity : AppCompatActivity() {

    private lateinit var linkVideo: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_detail)

        linkVideo = intent.getStringExtra("link_video").subSequence(32, 43).toString()

        initView()
    }

    private fun initView() {
        lifecycle.addObserver(youtube_player_view)

        youtube_player_view.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                val videoId = linkVideo
                youTubePlayer.loadVideo(videoId, 0f)
            }
        })
    }
}
