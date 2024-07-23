package com.sep.quiz.domain.usecase.quiz

import com.sep.quiz.domain.repository.quiz.QuizRepository
import javax.inject.Inject

class CategoryInfoUseCase @Inject constructor(
    private val quizRepository: QuizRepository
) {

    suspend operator fun invoke(categoryId: String) =
        quizRepository.fetchCategoryInfo(categoryId = categoryId)

}