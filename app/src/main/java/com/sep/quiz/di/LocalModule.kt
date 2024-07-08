package com.sep.quiz.di

import android.content.Context
import androidx.room.Room
import com.sep.quiz.data.local.QuestionDatabase
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
    ) : QuestionDatabase = Room.databaseBuilder(
        applicationContext,
        QuestionDatabase::class.java, "quiz-database"
    ).build()

}