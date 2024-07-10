package com.sep.quiz.domain.usecase.score

import com.sep.quiz.domain.repository.ScoreRepository
import javax.inject.Inject

class IncreaseScoreUseCase @Inject constructor(
    private val scoreRepository: ScoreRepository
) {

    suspend operator fun invoke(newIncrease: Int) = scoreRepository.increaseScore(newIncrease)

}