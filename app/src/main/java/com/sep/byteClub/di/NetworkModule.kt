package com.sep.byteClub.di

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sep.byteClub.data.remote.crypto.KucoinApiService
import com.sep.byteClub.data.remote.f1.F1ApiService
import com.sep.byteClub.data.remote.ninja.DadJokesApiService
import com.sep.byteClub.data.remote.ninja.DictionaryApiService
import com.sep.byteClub.data.remote.ninja.ImageToTextApiService
import com.sep.byteClub.data.remote.quiz.QuizApiService
import com.sep.byteClub.data.remote.weather.AirQualityApi
import com.sep.byteClub.data.remote.weather.WeatherApiService
import com.sep.byteClub.domain.AIR_QUALITY_BASE_URL
import com.sep.byteClub.domain.BASE_URL
import com.sep.byteClub.domain.DICTIONARY_BASE_URL
import com.sep.byteClub.domain.F1_BASE_URL
import com.sep.byteClub.domain.FORECAST_BASE_URL
import com.sep.byteClub.domain.KUCOIN_BASE_URL
import com.sep.byteClub.utils.NetworkConnection
import com.sep.byteClub.utils.callAdapter.NetworkResponseAdapterFactory
import com.sep.byteClub.utils.interceptor.ForceCacheInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.ConnectionSpec
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.TlsVersion
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val READ_TIMEOUT = 30L
    private const val WRITE_TIMEOUT = 30L
    private const val CONNECTION_TIMEOUT = 10L

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class QuizRetrofit

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class F1Retrofit

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class NinjasRetrofit

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class KucoinRetrofit

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class ForecastRetrofit

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class AirQualityRetrofit


    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun providerNetworkConnection(@ApplicationContext context: Context): NetworkConnection =
        NetworkConnection(context)

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    fun provideConnectionSpecs(): List<ConnectionSpec> {
        return listOf(
            ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                .tlsVersions(TlsVersion.TLS_1_2, TlsVersion.TLS_1_3)
                .build()
        )
    }

    @Provides
    @Singleton
    internal fun provideForceCacheInterceptor(networkConnection: NetworkConnection): Interceptor =
        ForceCacheInterceptor(networkConnection)

    @Provides
    @Singleton
    fun provideOkHttpClient(
        forceCacheInterceptor: Interceptor,
        connectionSpecList: List<ConnectionSpec>,
        interceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        .connectionSpecs(connectionSpecList)
        .addInterceptor(forceCacheInterceptor)
        .addInterceptor(interceptor)
        .build()


    @QuizRetrofit
    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        json: Json,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .addCallAdapterFactory(NetworkResponseAdapterFactory())
        .build()

    @Provides
    fun provideQuizApiService(@QuizRetrofit retrofit: Retrofit): QuizApiService = retrofit.create(
        QuizApiService::class.java
    )

    @NinjasRetrofit
    @Provides
    @Singleton
    fun provideDictionaryRetrofit(
        okHttpClient: OkHttpClient,
        json: Json,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(DICTIONARY_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .addCallAdapterFactory(NetworkResponseAdapterFactory())
        .build()

    @F1Retrofit
    @Provides
    @Singleton
    fun provideF1Retrofit(
        okHttpClient: OkHttpClient,
        json: Json,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(F1_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .addCallAdapterFactory(NetworkResponseAdapterFactory())
        .build()

    @KucoinRetrofit
    @Provides
    @Singleton
    fun provideKucoinRetrofit(
        okHttpClient: OkHttpClient,
        json: Json,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(KUCOIN_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .addCallAdapterFactory(NetworkResponseAdapterFactory())
        .build()

    @ForecastRetrofit
    @Provides
    @Singleton
    fun provideForecastRetrofit(
        okHttpClient: OkHttpClient,
        json: Json,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(FORECAST_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .addCallAdapterFactory(NetworkResponseAdapterFactory())
        .build()

    @AirQualityRetrofit
    @Provides
    @Singleton
    fun provideAirQualityRetrofit(
        okHttpClient: OkHttpClient,
        json: Json,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(AIR_QUALITY_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .addCallAdapterFactory(NetworkResponseAdapterFactory())
        .build()

    @Provides
    fun provideDictionaryApiService(@NinjasRetrofit retrofit: Retrofit): DictionaryApiService =
        retrofit.create(
            DictionaryApiService::class.java
        )

    @Provides
    fun provideDadJokeApiService(@NinjasRetrofit retrofit: Retrofit): DadJokesApiService =
        retrofit.create(
            DadJokesApiService::class.java
        )

    @Provides
    fun provideImageToTextApiService(@NinjasRetrofit retrofit: Retrofit): ImageToTextApiService =
        retrofit.create(
            ImageToTextApiService::class.java
        )

    @Provides
    fun provideF1ApiService(@F1Retrofit retrofit: Retrofit): F1ApiService =
        retrofit.create(F1ApiService::class.java)

    @Provides
    fun provideKucoinApiService(@KucoinRetrofit retrofit: Retrofit): KucoinApiService =
        retrofit.create(
            KucoinApiService::class.java
        )

    @Provides
    fun provideForecastApiService(@ForecastRetrofit retrofit: Retrofit): WeatherApiService =
        retrofit.create(
            WeatherApiService::class.java
        )

    @Provides
    fun provideAirQualityApiService(@AirQualityRetrofit retrofit: Retrofit): AirQualityApi =
        retrofit.create(
            AirQualityApi::class.java
        )

}