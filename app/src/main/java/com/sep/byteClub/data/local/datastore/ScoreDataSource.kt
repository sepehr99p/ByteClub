package com.sep.byteClub.data.local.datastore

import androidx.datastore.core.DataStore
import com.sep.byteClub.Score
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ScoreDataSource @Inject constructor(
    private val dataStore: DataStore<Score>
) {

    fun getScore(): Flow<Score> = dataStore.data

    suspend fun increaseScore(newScore: Int) {
        try {
            dataStore.updateData { preferences ->
                Score.newBuilder()
                    .setScore(preferences.score + newScore)
                    .build()
            }
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun decreaseScore(newScore: Int) {
        try {
            dataStore.updateData { preferences ->
                Score.newBuilder()
                    .setScore(preferences.score - newScore)
                    .build()
            }
        } catch (e: Exception) {
            throw e
        }
    }

}