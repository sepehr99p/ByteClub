package com.sep.byteClub.data.repository.quiz

import com.sep.byteClub.Score
import com.sep.byteClub.data.local.datastore.ScoreDataSource
import com.sep.byteClub.domain.repository.quiz.ScoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ScoreRepositoryImpl @Inject constructor(
    private val scoreDataSource: ScoreDataSource
) : ScoreRepository {
    override suspend fun getScore(): Flow<Score> = scoreDataSource.getScore()

    override suspend fun increaseScore(newScore: Int) = scoreDataSource.increaseScore(newScore)

    override suspend fun decreaseScore(newScore: Int) = scoreDataSource.decreaseScore(newScore)


}