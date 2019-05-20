package com.mahesaiqbal.bencanakita.model.dokumentasi

data class Dokumentasi(
    val `data`: List<Data>,
    val error: Boolean,
    val success_msg: String
)