package com.sep.quiz.domain.usecase

import com.sep.quiz.domain.repository.QuizRepository
import javax.inject.Inject

class InquiryUseCase @Inject constructor(
    private val quizRepository: QuizRepository
) {

    //todo : to be completed later
    suspend operator fun invoke() = quizRepository.inquiry()

}