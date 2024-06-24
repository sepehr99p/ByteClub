package com.sep.quiz.domain.repository

import com.sep.quiz.domain.entiry.CategoryEntity
import com.sep.quiz.domain.entiry.CategoryInfo
import com.sep.quiz.utils.ResultState

interface QuizRepository {

    suspend fun retrieveToken()

    suspend fun resetToken(oldToken : String)

    suspend fun inquiry()

    suspend fun fetchCategory() : ResultState<List<CategoryEntity>>

    suspend fun fetchCategoryInfo(categoryId : String) : ResultState<CategoryInfo>

}