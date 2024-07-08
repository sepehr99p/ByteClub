package com.sep.quiz.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sep.quiz.data.local.entity.QuestionDto

@Database(entities = [QuestionDto::class], version = 1)
abstract class QuestionDatabase : RoomDatabase() {
    abstract fun userDao(): QuestionDao
}