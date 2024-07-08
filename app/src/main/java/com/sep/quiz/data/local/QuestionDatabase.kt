package com.sep.quiz.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sep.quiz.data.local.dao.CategoryDao
import com.sep.quiz.data.local.dao.QuestionDao
import com.sep.quiz.data.local.entity.CategoryDto
import com.sep.quiz.data.local.entity.QuestionDto

@Database(entities = [QuestionDto::class, CategoryDto::class], version = 2)
abstract class QuestionDatabase : RoomDatabase() {
    abstract fun userDao(): QuestionDao
    abstract fun categoryDao(): CategoryDao
}