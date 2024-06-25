package com.sep.quiz.domain.usecase

import com.sep.quiz.domain.entiry.QuestionDifficulty
import com.sep.quiz.domain.entiry.QuestionType
import com.sep.quiz.domain.repository.QuizRepository
import javax.inject.Inject

class InquiryUseCase @Inject constructor(
    private val quizRepository: QuizRepository
) {

    suspend operator fun invoke(
        amount : Int,
        difficulty: QuestionDifficulty,
        type : QuestionType,
        categoryId : String
    ) = quizRepository.inquiry(
        amount = amount,
        difficulty = difficulty,
        type = type,
        categoryId = categoryId
    )

}