package com.sep.quiz.di

import com.sep.quiz.data.local.database.QuestionDatabase
import com.sep.quiz.data.remote.DadJokesApiService
import com.sep.quiz.data.remote.DictionaryApiService
import com.sep.quiz.data.remote.QuizApiService
import com.sep.quiz.data.remote.crypto.KucoinApiService
import com.sep.quiz.data.repository.DadJokeRepositoryImpl
import com.sep.quiz.data.repository.DictionaryRepositoryImpl
import com.sep.quiz.data.repository.KucoinRepositoryImpl
import com.sep.quiz.data.repository.QuizRepositoryImpl
import com.sep.quiz.domain.repository.DadJokeRepository
import com.sep.quiz.domain.repository.DictionaryRepository
import com.sep.quiz.domain.repository.KucoinRepository
import com.sep.quiz.domain.repository.QuizRepository
import com.sep.quiz.domain.usecase.CategoryInfoUseCase
import com.sep.quiz.domain.usecase.FetchCategoriesUseCase
import com.sep.quiz.domain.usecase.InquiryUseCase
import com.sep.quiz.domain.usecase.dictionary.GetWordUseCase
import com.sep.quiz.domain.usecase.joke.DadJokeUseCase
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

    @Singleton
    @Provides
    fun provideDictionaryRepository(
        dictionaryApiService: DictionaryApiService
    ): DictionaryRepository = DictionaryRepositoryImpl(
        dictionaryApiService = dictionaryApiService
    )

    @Singleton
    @Provides
    fun provideDadJokeRepository(
        dadJokesApiService: DadJokesApiService
    ): DadJokeRepository = DadJokeRepositoryImpl(
        dadJokesApiService = dadJokesApiService
    )

    @Singleton
    @Provides
    fun provideKucoinRepository(
        kucoinApiService: KucoinApiService
    ): KucoinRepository = KucoinRepositoryImpl(kucoinApiService = kucoinApiService)


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

    @Provides
    fun provideWordUseCase(
        dictionaryRepository: DictionaryRepository
    ): GetWordUseCase = GetWordUseCase(
        dictionaryRepository = dictionaryRepository
    )

    @Provides
    fun provideDadJokeUseCase(
        dadJokeRepository: DadJokeRepository
    ): DadJokeUseCase = DadJokeUseCase(
        dadJokeRepository = dadJokeRepository
    )

}