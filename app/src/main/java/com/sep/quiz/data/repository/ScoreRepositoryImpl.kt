package com.sep.quiz.data.repository

import com.sep.quiz.Score
import com.sep.quiz.data.local.datastore.ScoreDataSource
import com.sep.quiz.domain.repository.ScoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ScoreRepositoryImpl @Inject constructor(
    private val scoreDataSource: ScoreDataSource
) : ScoreRepository {
    override suspend fun getScore(): Flow<Score> = scoreDataSource.getScore()

    override suspend fun increaseScore(newScore: Int) = scoreDataSource.increaseScore(newScore)

    override suspend fun decreaseScore(newScore: Int) = scoreDataSource.decreaseScore(newScore)


}