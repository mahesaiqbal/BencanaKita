package com.mahesaiqbal.bencanakita.model.map

data class Map(
    val `data`: List<Data>,
    val error: Boolean,
    val success_msg: String
)