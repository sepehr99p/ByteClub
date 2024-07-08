package com.sep.quiz.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sep.quiz.domain.entiry.QuestionEntity

@Entity(tableName = "questions")
data class QuestionDto(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val category: String,
    val questionDescription: String,
    val correctAnswer: String,
    val firstIncorrectAnswers: String,
    val secondIncorrectAnswers: String? = null,
    val thirdIncorrectAnswers: String? = null,
    val fourthIncorrectAnswers: String? = null,
) {
    fun toDomainModel(): QuestionEntity = QuestionEntity(
        questionDescription = questionDescription,
        correctAnswer = correctAnswer,
        incorrectAnswers = arrayListOf<String>().apply {
            add(firstIncorrectAnswers)
            secondIncorrectAnswers?.let { add(it) }
            thirdIncorrectAnswers?.let { add(it) }
            fourthIncorrectAnswers?.let { add(it) }
        }
    )
}
