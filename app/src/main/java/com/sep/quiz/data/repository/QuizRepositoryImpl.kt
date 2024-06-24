package com.sep.quiz.data.repository

import com.sep.quiz.data.remote.QuizApiService
import com.sep.quiz.utils.ResultState
import com.sep.quiz.utils.toResultState
import com.sep.quiz.domain.repository.QuizRepository
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(
    private val quizApiService: QuizApiService
) : QuizRepository {

    override suspend fun retrieveToken() {
        // todo : save token into dataStore
        quizApiService.retrieveToken().toResultState(
            onSuccess = {
                ResultState.Success(it.token)
            }
        )
    }

    override suspend fun resetToken(oldToken : String) {
        //todo : save new token in dataStore
        quizApiService.resetToken(oldToken).toResultState(
            onSuccess = {
                ResultState.Success(it.token)
            }
        )
    }

    override suspend fun inquiry() {
        TODO("Not yet implemented")
    }

    override suspend fun fetchCategory() =
        quizApiService.fetchCategory().toResultState(onSuccess = { categoryResponse ->
            ResultState.Success(categoryResponse.categories.map { it.toDomainModel() })
        })

    override suspend fun fetchQuestionsCount(categoryId : String) =
        quizApiService.fetchQuestionsCount(categoryId).toResultState(onSuccess = {
            ResultState.Success(it.categoryCountInfo.toDomainModel())
        })
}