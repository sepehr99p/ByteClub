package com.sep.quiz.data.local.datastore.secretHitler

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.google.protobuf.InvalidProtocolBufferException
import com.sep.quiz.SecretHitlerPlayer
import java.io.InputStream
import java.io.OutputStream

object SecretHitlerSerializer : Serializer<SecretHitlerPlayer> {
    override val defaultValue: SecretHitlerPlayer
        get() = SecretHitlerPlayer.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): SecretHitlerPlayer {
        try {
            return SecretHitlerPlayer.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: SecretHitlerPlayer, output: OutputStream) {
        t.writeTo(output)
    }


    val Context.secretHitlerDataStoreFile: DataStore<SecretHitlerPlayer> by dataStore(
        fileName = "secretHitlerPlayer.pb",
        serializer = SecretHitlerSerializer
    )

}