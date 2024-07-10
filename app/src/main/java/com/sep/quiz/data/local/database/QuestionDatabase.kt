package com.sep.quiz.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sep.quiz.data.local.database.dao.CategoryDao
import com.sep.quiz.data.local.database.dao.QuestionDao
import com.sep.quiz.data.local.database.entity.CategoryDto
import com.sep.quiz.data.local.database.entity.QuestionDto

@Database(entities = [QuestionDto::class, CategoryDto::class], version = 2)
abstract class QuestionDatabase : RoomDatabase() {
    abstract fun userDao(): QuestionDao
    abstract fun categoryDao(): CategoryDao
}