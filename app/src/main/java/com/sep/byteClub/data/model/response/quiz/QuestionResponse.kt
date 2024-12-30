package com.sep.byteClub.data.model.response.quiz

import com.sep.byteClub.data.local.database.entity.QuestionDto
import com.sep.byteClub.domain.entity.QuestionEntity
import com.sep.byteClub.utils.callAdapter.BaseNetworkResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuestionResponse(
    @SerialName("results") val questionList: List<QuestionResponseDto>
) : BaseNetworkResponse()

@Serializable
data class QuestionResponseDto(
    @SerialName("type") val type: String,
    @SerialName("difficulty") val difficulty: String,
    @SerialName("category") val category: String,
    @SerialName("question") val questionDescription: String,
    @SerialName("correct_answer") val correctAnswer: String,
    @SerialName("incorrect_answers") val incorrectAnswers: List<String>
) {
    fun toDomainModel(): QuestionEntity = QuestionEntity(
        questionDescription = this.questionDescription,
        correctAnswer = this.correctAnswer,
        incorrectAnswers = this.incorrectAnswers
    )

    fun toDatabaseDto(): QuestionDto {

        return QuestionDto(
            category = category,
            questionDescription = questionDescription,
            correctAnswer = correctAnswer,
            firstIncorrectAnswers = incorrectAnswers[0],
            secondIncorrectAnswers = if (incorrectAnswers.size >= 2) incorrectAnswers[1] else null,
            thirdIncorrectAnswers = if (incorrectAnswers.size >= 3) incorrectAnswers[2] else null,
            fourthIncorrectAnswers = if (incorrectAnswers.size >= 4) incorrectAnswers[3] else null
        )
    }
}
