package com.sep.byteClub.data.repository.quiz

import com.sep.byteClub.data.local.database.QuestionDatabase
import com.sep.byteClub.data.remote.quiz.QuizApiService
import com.sep.byteClub.domain.entiry.CategoryEntity
import com.sep.byteClub.domain.entiry.CategoryInfo
import com.sep.byteClub.domain.entiry.QuestionEntity
import com.sep.byteClub.domain.repository.quiz.QuizRepository
import com.sep.byteClub.utils.NetworkConnection
import com.sep.byteClub.utils.ResultState
import com.sep.byteClub.utils.toResultState
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

    override suspend fun fetchCategory(): ResultState<List<CategoryEntity>> {
        return if (networkConnection.isInternetOn()) {
            quizApiService.fetchCategory().toResultState(onSuccess = { categoryResponse ->
                withContext(Dispatchers.IO) {
                    questionDatabase.categoryDao()
                        .insertAll(*categoryResponse.categories.map { it.toDatabaseDto() }
                            .toTypedArray())
                }
                ResultState.Success(categoryResponse.categories.map { it.toDomainModel() })
            })
        } else {
            val cachedData = questionDatabase.categoryDao().fetchAll()
            if (cachedData.isEmpty()) {
                ResultState.Failure("No cached data available")
            } else {
                ResultState.Success(cachedData.map { it.toDomainModel() })
            }
        }
    }

    override suspend fun fetchCategoryInfo(categoryId: String): ResultState<CategoryInfo> =
        quizApiService.fetchQuestionsCount(categoryId).toResultState(onSuccess = {
            ResultState.Success(it.categoryCountInfo.toDomainModel())
        })
}