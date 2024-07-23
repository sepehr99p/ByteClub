package com.sep.quiz.di

import com.sep.quiz.data.local.database.QuestionDatabase
import com.sep.quiz.data.remote.ninja.DadJokesApiService
import com.sep.quiz.data.remote.ninja.DictionaryApiService
import com.sep.quiz.data.remote.quiz.QuizApiService
import com.sep.quiz.data.remote.crypto.KucoinApiService
import com.sep.quiz.data.repository.ninja.DadJokeRepositoryImpl
import com.sep.quiz.data.repository.ninja.DictionaryRepositoryImpl
import com.sep.quiz.data.repository.crypto.KucoinRepositoryImpl
import com.sep.quiz.data.repository.quiz.QuizRepositoryImpl
import com.sep.quiz.domain.repository.ninja.DadJokeRepository
import com.sep.quiz.domain.repository.ninja.DictionaryRepository
import com.sep.quiz.domain.repository.crypto.KucoinRepository
import com.sep.quiz.domain.repository.quiz.QuizRepository
import com.sep.quiz.domain.usecase.quiz.CategoryInfoUseCase
import com.sep.quiz.domain.usecase.quiz.FetchCategoriesUseCase
import com.sep.quiz.domain.usecase.quiz.InquiryUseCase
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