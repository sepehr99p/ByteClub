package com.sep.quiz.data.repository

import com.sep.quiz.data.local.QuestionDatabase
import com.sep.quiz.data.remote.QuizApiService
import com.sep.quiz.domain.entiry.CategoryEntity
import com.sep.quiz.domain.entiry.CategoryInfo
import com.sep.quiz.domain.entiry.QuestionEntity
import com.sep.quiz.domain.repository.QuizRepository
import com.sep.quiz.utils.NetworkConnection
import com.sep.quiz.utils.ResultState
import com.sep.quiz.utils.toResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(
    private val quizApiService: QuizApiService,
    private val questionDatabase: QuestionDatabase,
    private val networkConnection: NetworkConnection
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
    ): ResultState<List<QuestionEntity>> {
        if (networkConnection.isInternetOn()) {
            return quizApiService.inquiry(
                amount = amount,
                difficulty = difficulty,
//            type = type.name.lowercase(),
                category = categoryId
            ).toResultState(onSuccess = { questionResponse ->
                withContext(Dispatchers.IO) {
                    questionDatabase.userDao()
                        .insertAll(*questionResponse.questionList.map { it.toDatabaseDto() }
                            .toTypedArray())
                }
                ResultState.Success(questionResponse.questionList.map { it.toDomainModel() })
            })
        } else {
            val cachedData =
                questionDatabase.userDao()
                    .loadAllByCategory(
                        questionDatabase.categoryDao().loadById(categoryId.toInt()).name
                    )
                    .map { it.toDomainModel() }
            return if (cachedData.isEmpty()) {
                ResultState.Failure("No cached question available")
            } else {
                ResultState.Success(cachedData)
            }
        }
    }

    override suspend fun fetchCategory(): ResultState<List<CategoryEntity>> =
        quizApiService.fetchCategory().toResultState(onSuccess = { categoryResponse ->
            withContext(Dispatchers.IO) {
                questionDatabase.categoryDao()
                    .insertAll(*categoryResponse.categories.map { it.toDatabaseDto() }
                        .toTypedArray())
            }
            ResultState.Success(categoryResponse.categories.map { it.toDomainModel() })
        })

    override suspend fun fetchCategoryInfo(categoryId: String): ResultState<CategoryInfo> =
        quizApiService.fetchQuestionsCount(categoryId).toResultState(onSuccess = {
            ResultState.Success(it.categoryCountInfo.toDomainModel())
        })
}