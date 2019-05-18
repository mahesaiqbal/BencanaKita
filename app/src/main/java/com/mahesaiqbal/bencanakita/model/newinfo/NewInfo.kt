package com.mahesaiqbal.bencanakita.model.newinfo

data class NewInfo(
    val data: List<Data>,
    val error: Boolean,
    val success_msg: String
)