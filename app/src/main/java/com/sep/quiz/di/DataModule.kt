package com.sep.quiz.di

import com.sep.quiz.data.local.database.QuestionDatabase
import com.sep.quiz.data.remote.QuizApiService
import com.sep.quiz.data.repository.QuizRepositoryImpl
import com.sep.quiz.domain.repository.QuizRepository
import com.sep.quiz.domain.usecase.CategoryInfoUseCase
import com.sep.quiz.domain.usecase.FetchCategoriesUseCase
import com.sep.quiz.domain.usecase.InquiryUseCase
import com.sep.quiz.utils.NetworkConnection
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideQuizRepository(
        quizApiService: QuizApiService,
        questionDatabase: QuestionDatabase,
        networkConnection: NetworkConnection
    ): QuizRepository =
        QuizRepositoryImpl(
            quizApiService = quizApiService,
            questionDatabase = questionDatabase,
            networkConnection = networkConnection
        )

    @Provides
    fun provideCategoryInfoUseCase(
        quizRepository: QuizRepository
    ): CategoryInfoUseCase = CategoryInfoUseCase(quizRepository = quizRepository)

    @Provides
    fun provideCategoriesUseCase(
        quizRepository: QuizRepository
    ): FetchCategoriesUseCase = FetchCategoriesUseCase(quizRepository = quizRepository)

    @Provides
    fun provideInquiryUseCase(
        quizRepository: QuizRepository
    ): InquiryUseCase = InquiryUseCase(quizRepository = quizRepository)

}