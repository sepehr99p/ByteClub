package com.sep.byteClub.domain.usecase.quiz.score

import com.sep.byteClub.domain.repository.quiz.ScoreRepository
import javax.inject.Inject

class IncreaseScoreUseCase @Inject constructor(
    private val scoreRepository: ScoreRepository
) {

    suspend operator fun invoke(newIncrease: Int) = scoreRepository.increaseScore(newIncrease)

}