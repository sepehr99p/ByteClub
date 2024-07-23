package com.sep.quiz.domain.usecase.quiz.score

import com.sep.quiz.domain.repository.quiz.ScoreRepository
import javax.inject.Inject

class DecreaseScoreUseCase @Inject constructor(
    private val scoreRepository: ScoreRepository
) {

    suspend operator fun invoke(newScore: Int) = scoreRepository.decreaseScore(newScore)

}