package com.sep.byteClub.domain.usecase.quiz

import com.sep.byteClub.domain.repository.quiz.QuizRepository
import javax.inject.Inject

class CategoryInfoUseCase @Inject constructor(
    private val quizRepository: QuizRepository
) {

    suspend operator fun invoke(categoryId: String) =
        quizRepository.fetchCategoryInfo(categoryId = categoryId)

}