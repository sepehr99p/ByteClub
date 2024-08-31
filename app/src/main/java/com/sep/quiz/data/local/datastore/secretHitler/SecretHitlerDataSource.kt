package com.sep.quiz.data.local.datastore.secretHitler

import androidx.datastore.core.DataStore
import com.sep.quiz.Score
import com.sep.quiz.SecretHitlerPlayer
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SecretHitlerDataSource @Inject constructor(
    private val dataStore: DataStore<SecretHitlerPlayer>
) {

    fun getPlayers(): Flow<SecretHitlerPlayer> = dataStore.data

    suspend fun addPlateList(playersListPreferences: SecretHitlerPlayer) {
        dataStore.updateData {
            SecretHitlerPlayer.newBuilder(playersListPreferences).build()
        }
    }

    suspend fun deleteAll() {
        try {
            dataStore.updateData {
                it.toBuilder().clear().build()
            }
        } catch (e: Exception) {
            throw e
        }
    }

}