package com.mahesaiqbal.bencanakita.utils

import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL

class DownloadPdf {

    companion object {
        fun DownloadFile(fileURL: String, directory: File) {
            try {

                val fileOutputStream = FileOutputStream(directory)
                val url = URL(fileURL)
                val connection = url.openConnection() as HttpURLConnection
                connection.setRequestMethod("GET")
                connection.setDoOutput(true)
                connection.connect()

                val inputStream = connection.getInputStream()

                val buffer = ByteArray(1024)
                val len1 = inputStream.read(buffer)

                while (len1 > 0) {
                    fileOutputStream.write(buffer, 0, len1)
                }

                fileOutputStream.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}