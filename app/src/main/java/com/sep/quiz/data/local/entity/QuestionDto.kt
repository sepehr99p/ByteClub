package com.sep.quiz.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class QuestionDto(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val category : String,
    val questionDescription: String,
    val correctAnswer: String,
    val firstIncorrectAnswers : String,
    val secondIncorrectAnswers : String? = null,
    val thirdIncorrectAnswers : String? = null,
    val fourthIncorrectAnswers : String? = null,
)
