package com.mahesaiqbal.bencanakita.utils

import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class FileDownloader {
    companion object {
        private val MEGABYTE = 1024 * 1024

        fun downloadFile(fileUrl: String, directory: File) {
            try {

                val url = URL(fileUrl)
                val urlConnection = url.openConnection() as HttpURLConnection
                //urlConnection.setRequestMethod("GET");
                //urlConnection.setDoOutput(true);
                urlConnection.connect()

                val inputStream = urlConnection.getInputStream()
                val fileOutputStream = FileOutputStream(directory)
                val totalSize = urlConnection.getContentLength()

                val buffer = ByteArray(MEGABYTE)
                val bufferLength = inputStream.read(buffer)

                while (bufferLength > 0) {
                    fileOutputStream.write(buffer, 0, bufferLength)
                }

                fileOutputStream.close()
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: MalformedURLException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
    }
}