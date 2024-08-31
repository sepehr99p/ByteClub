package com.sep.byteClub.domain.usecase.quiz

import com.sep.byteClub.domain.repository.quiz.QuizRepository
import javax.inject.Inject

class FetchCategoriesUseCase @Inject constructor(
    private val quizRepository: QuizRepository
) {

    suspend operator fun invoke() = quizRepository.fetchCategory()

}