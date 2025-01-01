package com.sep.byteClub.ui.utils

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import androidx.core.content.FileProvider.getUriForFile
import java.io.File
import com.sep.byteClub.R

class ComposeFileProvider : FileProvider(
    R.xml.filepaths
) {
    companion object {
        fun getImageUri(context: Context): Uri {
            val directory = File(context.applicationContext.cacheDir, "images")
            directory.mkdirs()
            val file = File.createTempFile(
                "selected_image_",
                ".jpg",
                directory,
            )
            val authority = context.applicationContext.packageName + ".provider"
            return getUriForFile(
                context.applicationContext,
                authority,
                file,
            )
        }
    }
}
