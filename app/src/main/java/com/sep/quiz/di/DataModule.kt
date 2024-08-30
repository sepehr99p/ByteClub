package com.sep.quiz.di

import android.content.Context
import com.sep.quiz.data.local.database.QuestionDatabase
import com.sep.quiz.data.remote.crypto.KucoinApiService
import com.sep.quiz.data.remote.ninja.DadJokesApiService
import com.sep.quiz.data.remote.ninja.DictionaryApiService
import com.sep.quiz.data.remote.quiz.QuizApiService
import com.sep.quiz.data.remote.weather.AirQualityApi
import com.sep.quiz.data.remote.weather.WeatherApiService
import com.sep.quiz.data.repository.crypto.KucoinRepositoryImpl
import com.sep.quiz.data.repository.ninja.DadJokeRepositoryImpl
import com.sep.quiz.data.repository.ninja.DictionaryRepositoryImpl
import com.sep.quiz.data.repository.quiz.QuizRepositoryImpl
import com.sep.quiz.data.repository.secretHitler.SecretHitlerRepositoryImpl
import com.sep.quiz.data.repository.weather.AirQualityRepositoryImpl
import com.sep.quiz.data.repository.weather.WeatherRepositoryImpl
import com.sep.quiz.domain.repository.crypto.KucoinRepository
import com.sep.quiz.domain.repository.ninja.DadJokeRepository
import com.sep.quiz.domain.repository.ninja.DictionaryRepository
import com.sep.quiz.domain.repository.quiz.QuizRepository
import com.sep.quiz.domain.repository.secretHitler.SecretHitlerRepository
import com.sep.quiz.domain.repository.weather.AirQualityRepository
import com.sep.quiz.domain.repository.weather.WeatherRepository
import com.sep.quiz.domain.usecase.dictionary.GetWordUseCase
import com.sep.quiz.domain.usecase.joke.DadJokeUseCase
import com.sep.quiz.domain.usecase.quiz.CategoryInfoUseCase
import com.sep.quiz.domain.usecase.quiz.FetchCategoriesUseCase
import com.sep.quiz.domain.usecase.quiz.InquiryUseCase
import com.sep.quiz.domain.usecase.weather.AirQualityUseCase
import com.sep.quiz.domain.usecase.weather.CurrentWeatherUseCase
import com.sep.quiz.domain.usecase.weather.ForecastWeatherUseCase
import com.sep.quiz.ui.utils.GPSHelper
import com.sep.quiz.utils.NetworkConnection
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Singleton
    @Provides
    fun provideForecastRepository(
        weatherApiService: WeatherApiService
    ): WeatherRepository = WeatherRepositoryImpl(weatherApiService = weatherApiService)

    @Singleton
    @Provides
    fun provideAirQualityRepository(
        airQualityApi: AirQualityApi
    ): AirQualityRepository = AirQualityRepositoryImpl(airQualityApi = airQualityApi)

    @Singleton
    @Provides
    fun provideSecretHitlerRepository() : SecretHitlerRepository = SecretHitlerRepositoryImpl()

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

    @Provides
    fun provideAirQualityUseCase(
        airQualityRepository: AirQualityRepository
    ): AirQualityUseCase = AirQualityUseCase(
        airQualityRepository = airQualityRepository
    )

    @Provides
    fun provideCurrentWeatherUseCase(
        weatherRepository: WeatherRepository
    ): CurrentWeatherUseCase = CurrentWeatherUseCase(
        weatherRepository = weatherRepository
    )

    @Provides
    fun provideForecastWeatherUseCase(
        weatherRepository: WeatherRepository
    ): ForecastWeatherUseCase = ForecastWeatherUseCase(
        weatherRepository = weatherRepository
    )

    @Singleton
    @Provides
    fun provideGpsHelper(
        @ApplicationContext appContext: Context
    ): GPSHelper = GPSHelper(appContext)

}