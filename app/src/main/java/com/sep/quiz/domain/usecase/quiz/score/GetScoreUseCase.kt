package com.sep.quiz.domain.usecase.quiz.score

import com.sep.quiz.domain.repository.quiz.ScoreRepository
import javax.inject.Inject

class GetScoreUseCase @Inject constructor(
    private val scoreRepository: ScoreRepository
) {

    suspend operator fun invoke() = scoreRepository.getScore()

}