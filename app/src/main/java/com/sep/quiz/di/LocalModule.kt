package com.sep.quiz.di

import android.content.Context
import androidx.room.Room
import com.sep.quiz.data.local.database.QuestionDatabase
import com.sep.quiz.data.local.datastore.ScoreDataSource
import com.sep.quiz.data.repository.ScoreRepositoryImpl
import com.sep.quiz.domain.repository.ScoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

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

}