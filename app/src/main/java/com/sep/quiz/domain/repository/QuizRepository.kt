package com.sep.quiz.domain.repository

import com.sep.quiz.domain.entiry.CategoryEntity
import com.sep.quiz.domain.entiry.CategoryInfo
import com.sep.quiz.domain.entiry.QuestionDifficulty
import com.sep.quiz.domain.entiry.QuestionEntity
import com.sep.quiz.domain.entiry.QuestionType
import com.sep.quiz.utils.ResultState

interface QuizRepository {

    suspend fun retrieveToken()

    suspend fun resetToken(oldToken: String)

    suspend fun inquiry(
        amount: Int,
        difficulty: String ,
        type: String ,
        categoryId: String
    ): ResultState<List<QuestionEntity>>

    suspend fun fetchCategory(): ResultState<List<CategoryEntity>>

    suspend fun fetchCategoryInfo(categoryId: String): ResultState<CategoryInfo>

}