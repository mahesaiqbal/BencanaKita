package com.mahesaiqbal.bencanakita.utils

import android.os.Environment
import android.os.Environment.MEDIA_MOUNTED


class CheckForSDCard {
    fun isSDCardPresent(): Boolean {
        return Environment.getExternalStorageState() == MEDIA_MOUNTED
    }
}