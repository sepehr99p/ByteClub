package com.sep.quiz.data.repository

import android.util.Log
import com.sep.quiz.data.remote.QuizApiService
import com.sep.quiz.domain.entiry.QuestionDifficulty
import com.sep.quiz.domain.entiry.QuestionEntity
import com.sep.quiz.domain.entiry.QuestionType
import com.sep.quiz.domain.repository.QuizRepository
import com.sep.quiz.utils.ResultState
import com.sep.quiz.utils.toResultState
import javax.inject.Inject
import kotlin.math.log

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

    override suspend fun resetToken(oldToken: String) {
        //todo : save new token in dataStore
        quizApiService.resetToken(oldToken).toResultState(
            onSuccess = {
                ResultState.Success(it.token)
            }
        )
    }

    override suspend fun inquiry(
        amount: Int,
        difficulty: String,
        type: String,
        categoryId: String
    ) : ResultState<List<QuestionEntity>> {
        Log.i("TAG", "inquiry: ")
        return quizApiService.inquiry(
            amount = amount,
            difficulty = difficulty,
//            type = type.name.lowercase(),
            category = categoryId
        ).toResultState(onSuccess = { questionResponse ->
            ResultState.Success(questionResponse.questionList.map { it.toDomainModel() })
        })

    }

    override suspend fun fetchCategory() =
        quizApiService.fetchCategory().toResultState(onSuccess = { categoryResponse ->
            ResultState.Success(categoryResponse.categories.map { it.toDomainModel() })
        })

    override suspend fun fetchCategoryInfo(categoryId: String) =
        quizApiService.fetchQuestionsCount(categoryId).toResultState(onSuccess = {
            ResultState.Success(it.categoryCountInfo.toDomainModel())
        })
}