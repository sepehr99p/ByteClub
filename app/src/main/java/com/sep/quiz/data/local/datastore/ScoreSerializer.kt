package com.sep.quiz.data.local.datastore

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.google.protobuf.InvalidProtocolBufferException
import com.sep.quiz.Score
import java.io.InputStream
import java.io.OutputStream

object ScoreSerializer : Serializer<Score> {
    override val defaultValue: Score
        get() = Score.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): Score {
        try {
            return Score.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: Score, output: OutputStream) {
        t.writeTo(output)
    }


    val Context.scoreDataStoreFile: DataStore<Score> by dataStore(
        fileName = "score.pb",
        serializer = ScoreSerializer
    )

}