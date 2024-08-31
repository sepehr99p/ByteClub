package com.sep.byteClub.domain.usecase.quiz

import com.sep.byteClub.domain.entiry.QuestionDifficulty
import com.sep.byteClub.domain.entiry.QuestionType
import com.sep.byteClub.domain.repository.quiz.QuizRepository
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
        difficulty = difficulty.name.lowercase(),
        type = type.name.lowercase(),
        categoryId = categoryId
    )

}