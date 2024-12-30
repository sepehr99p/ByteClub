package com.sep.byteClub.domain.repository.quiz

import com.sep.byteClub.domain.entity.CategoryEntity
import com.sep.byteClub.domain.entity.CategoryInfo
import com.sep.byteClub.domain.entity.QuestionEntity
import com.sep.byteClub.utils.ResultState

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