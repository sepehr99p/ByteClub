package com.sep.byteClub.data.model

import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okio.BufferedSink

class ProgressRequestBody(
    private val byteArray: ByteArray,
    private val contentType: String,
    private val progressCallback: (progress: Int) -> Unit
) : RequestBody() {

    override fun contentType(): MediaType? = contentType.toMediaTypeOrNull()

    override fun contentLength(): Long = byteArray.size.toLong()

    override fun writeTo(sink: BufferedSink) {
        val byteArrayLength = contentLength()
        var uploaded = 0L
        val buffer = ByteArray(2048)

        var index = 0
        while (index < byteArray.size) {
            val bytesToWrite = minOf(buffer.size, byteArray.size - index)
            System.arraycopy(byteArray, index, buffer, 0, bytesToWrite)
            sink.write(buffer, 0, bytesToWrite)
            uploaded += bytesToWrite
            index += bytesToWrite
            progressCallback((100 * uploaded / byteArrayLength).toInt())
        }
    }
}