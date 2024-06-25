package com.sep.quiz.data.model.response

import com.sep.quiz.domain.entiry.QuestionEntity
import com.sep.quiz.utils.callAdapter.BaseNetworkResponse
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
}
