package com.mahesaiqbal.bencanakita.model.video

data class Video(
    val data: List<Data>,
    val error: Boolean,
    val success_msg: String
)