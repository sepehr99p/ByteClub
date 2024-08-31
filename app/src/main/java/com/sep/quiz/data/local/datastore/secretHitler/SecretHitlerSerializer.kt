package com.sep.quiz.data.local.datastore.secretHitler

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.google.protobuf.InvalidProtocolBufferException
import com.sep.quiz.SecretHitlerPlayerListPreferences
import java.io.InputStream
import java.io.OutputStream

object SecretHitlerSerializer : Serializer<SecretHitlerPlayerListPreferences> {
    override val defaultValue: SecretHitlerPlayerListPreferences
        get() = SecretHitlerPlayerListPreferences.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): SecretHitlerPlayerListPreferences {
        try {
            return SecretHitlerPlayerListPreferences.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: SecretHitlerPlayerListPreferences, output: OutputStream) {
        t.writeTo(output)
    }


    val Context.secretHitlerDataStoreFile: DataStore<SecretHitlerPlayerListPreferences> by dataStore(
        fileName = "secretHitlerPlayer.pb",
        serializer = SecretHitlerSerializer
    )

}