package com.sep.quiz.data.local.datastore.secretHitler

import androidx.datastore.core.DataStore
import com.sep.quiz.SecretHitlerPlayer
import com.sep.quiz.SecretHitlerPlayerListPreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SecretHitlerDataSource @Inject constructor(
    private val dataStore: DataStore<SecretHitlerPlayerListPreferences>
) {

    fun getPlayers(): Flow<SecretHitlerPlayerListPreferences> = dataStore.data

    suspend fun addPlayersList(playersListPreferences: SecretHitlerPlayerListPreferences) {
        dataStore.updateData {
            SecretHitlerPlayerListPreferences.newBuilder(playersListPreferences).build()
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