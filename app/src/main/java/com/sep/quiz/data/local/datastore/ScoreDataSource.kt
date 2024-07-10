package com.sep.quiz.data.local.datastore

import androidx.datastore.core.DataStore
import com.sep.quiz.Score
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ScoreDataSource @Inject constructor(
    private val dataStore: DataStore<Score>
) {

    suspend fun getScore() : Flow<Score> {
        try {
            return dataStore.data
        } catch (e : Exception) {
            throw e
        }
    }

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