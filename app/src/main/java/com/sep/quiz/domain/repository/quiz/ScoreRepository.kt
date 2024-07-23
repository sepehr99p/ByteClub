package com.sep.quiz.domain.repository.quiz

import com.sep.quiz.Score
import kotlinx.coroutines.flow.Flow

interface ScoreRepository {

    suspend fun getScore(): Flow<Score>

    suspend fun increaseScore(newScore: Int)

    suspend fun decreaseScore(newScore: Int)

}