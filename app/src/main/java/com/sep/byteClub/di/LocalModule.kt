package com.sep.byteClub.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.dataStoreFile
import androidx.room.Room
import com.sep.byteClub.Score
import com.sep.byteClub.data.local.database.QuestionDatabase
import com.sep.byteClub.data.local.datastore.ScoreDataSource
import com.sep.byteClub.data.local.datastore.ScoreSerializer
import com.sep.byteClub.data.repository.quiz.ScoreRepositoryImpl
import com.sep.byteClub.domain.repository.quiz.ScoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    fun provideDatabase(
        @ApplicationContext applicationContext: Context
    ): QuestionDatabase = Room.databaseBuilder(
        applicationContext,
        QuestionDatabase::class.java, "quiz-database"
    ).allowMainThreadQueries().build()

    @Provides
    fun provideScoreRepository(
        scoreDataSource: ScoreDataSource
    ): ScoreRepository = ScoreRepositoryImpl(scoreDataSource)

    @Singleton
    @Provides
    fun provideScoreDataSource(
        @ApplicationContext context: Context,
    ): DataStore<Score> {
        return DataStoreFactory.create(
            serializer = ScoreSerializer,
            produceFile = { context.dataStoreFile("score.pb") },
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { Score.getDefaultInstance() },
            ),
            scope = CoroutineScope(SupervisorJob()),
        )
    }

}