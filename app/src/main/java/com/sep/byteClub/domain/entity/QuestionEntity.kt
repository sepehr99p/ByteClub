package com.sep.byteClub.domain.entity

data class QuestionEntity(
    val questionDescription: String,
    val correctAnswer: String,
    val incorrectAnswers: List<String>
)
