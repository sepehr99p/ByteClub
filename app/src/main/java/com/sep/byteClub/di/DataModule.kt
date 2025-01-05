package com.sep.byteClub.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.dataStoreFile
import com.sep.byteClub.SecretHitlerPlayerListPreferences
import com.sep.byteClub.data.local.database.QuestionDatabase
import com.sep.byteClub.data.local.datastore.secretHitler.SecretHitlerDataSource
import com.sep.byteClub.data.local.datastore.secretHitler.SecretHitlerSerializer
import com.sep.byteClub.data.remote.crypto.KucoinApiService
import com.sep.byteClub.data.remote.f1.F1ApiService
import com.sep.byteClub.data.remote.ninja.NinjaApiService
import com.sep.byteClub.data.remote.quiz.QuizApiService
import com.sep.byteClub.data.remote.weather.AirQualityApi
import com.sep.byteClub.data.remote.weather.WeatherApiService
import com.sep.byteClub.data.repository.crypto.KucoinRepositoryImpl
import com.sep.byteClub.data.repository.f1.F1RepositoryImpl
import com.sep.byteClub.data.repository.ninja.DadJokeRepositoryImpl
import com.sep.byteClub.data.repository.ninja.DictionaryRepositoryImpl
import com.sep.byteClub.data.repository.ninja.ImageToTextRepositoryImpl
import com.sep.byteClub.data.repository.quiz.QuizRepositoryImpl
import com.sep.byteClub.data.repository.secretHitler.SecretHitlerRepositoryImpl
import com.sep.byteClub.data.repository.weather.AirQualityRepositoryImpl
import com.sep.byteClub.data.repository.weather.WeatherRepositoryImpl
import com.sep.byteClub.domain.repository.crypto.KucoinRepository
import com.sep.byteClub.domain.repository.f1.F1Repository
import com.sep.byteClub.domain.repository.ninja.DadJokeRepository
import com.sep.byteClub.domain.repository.ninja.DictionaryRepository
import com.sep.byteClub.domain.repository.ninja.ImageToTextRepository
import com.sep.byteClub.domain.repository.quiz.QuizRepository
import com.sep.byteClub.domain.repository.secretHitler.SecretHitlerRepository
import com.sep.byteClub.domain.repository.weather.AirQualityRepository
import com.sep.byteClub.domain.repository.weather.WeatherRepository
import com.sep.byteClub.domain.usecase.dictionary.GetWordUseCase
import com.sep.byteClub.domain.usecase.joke.DadJokeUseCase
import com.sep.byteClub.domain.usecase.quiz.CategoryInfoUseCase
import com.sep.byteClub.domain.usecase.quiz.FetchCategoriesUseCase
import com.sep.byteClub.domain.usecase.quiz.InquiryUseCase
import com.sep.byteClub.domain.usecase.weather.AirQualityUseCase
import com.sep.byteClub.domain.usecase.weather.CurrentWeatherUseCase
import com.sep.byteClub.domain.usecase.weather.ForecastWeatherUseCase
import com.sep.byteClub.ui.utils.GPSHelper
import com.sep.byteClub.utils.NetworkConnection
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
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
        dictionaryApiService: NinjaApiService
    ): DictionaryRepository = DictionaryRepositoryImpl(
        dictionaryApiService = dictionaryApiService
    )

    @Singleton
    @Provides
    fun provideImageToTextRepository(
        imageToTextApiService: NinjaApiService
    ): ImageToTextRepository = ImageToTextRepositoryImpl(imageToTextApiService)

    @Singleton
    @Provides
    fun provideF1Repository(
        f1ApiService: F1ApiService
    ): F1Repository = F1RepositoryImpl(f1ApiService)

    @Singleton
    @Provides
    fun provideDadJokeRepository(
        dadJokesApiService: NinjaApiService
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
    fun provideSecretHitlerRepository(
        dataSource: SecretHitlerDataSource
    ): SecretHitlerRepository = SecretHitlerRepositoryImpl(dataSource = dataSource)

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

    @Singleton
    @Provides
    fun provideMyPlayersPreferencesDataStore(
        @ApplicationContext context: Context,
    ): DataStore<SecretHitlerPlayerListPreferences> {
        return DataStoreFactory.create(
            serializer = SecretHitlerSerializer,
            produceFile = { context.dataStoreFile("secretHitlerPlayer.pb") },
            corruptionHandler =
            ReplaceFileCorruptionHandler(
                produceNewData = { SecretHitlerPlayerListPreferences.getDefaultInstance() },
            ),
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
        )

    }

}