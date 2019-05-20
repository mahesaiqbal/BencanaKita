package com.mahesaiqbal.bencanakita.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mahesaiqbal.bencanakita.R
import com.mahesaiqbal.bencanakita.ui.adapter.VideoAdapter.VideoViewHolder
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import androidx.lifecycle.Lifecycle


class VideoAdapter(private val videoIds: ArrayList<String>, private val lifecycle: Lifecycle) :
    RecyclerView.Adapter<VideoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val youTubePlayerView = LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false) as YouTubePlayerView
        lifecycle.addObserver(youTubePlayerView)

        return VideoViewHolder(youTubePlayerView)
    }

    override fun onBindViewHolder(viewHolder: VideoViewHolder, position: Int) {
        viewHolder.cueVideo(videoIds[position])

    }

    override fun getItemCount(): Int = videoIds.size

    class VideoViewHolder(private val youTubePlayerView: YouTubePlayerView) :
        RecyclerView.ViewHolder(youTubePlayerView) {
        private var youTubePlayer: YouTubePlayer? = null
        private var currentVideoId: String? = null

        init {

            youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    this@VideoViewHolder.youTubePlayer = youTubePlayer
                    this@VideoViewHolder.youTubePlayer!!.cueVideo(currentVideoId!!, 0f)
                }
            })
        }

        fun cueVideo(videoId: String) {
            currentVideoId = videoId

            if (youTubePlayer == null)
                return

            youTubePlayer!!.cueVideo(videoId, 0f)
        }
    }
}
