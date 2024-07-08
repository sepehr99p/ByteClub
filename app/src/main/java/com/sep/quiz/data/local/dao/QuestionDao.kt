package com.sep.quiz.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sep.quiz.data.local.entity.QuestionDto

@Dao
interface QuestionDao {

    @Query("SELECT * FROM questions")
    fun fetchAll(): List<QuestionDto>

    @Query("SELECT * FROM questions WHERE category IS (:cat)")
    fun loadAllByCategory(cat: String): List<QuestionDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg questions: QuestionDto)

    @Delete
    fun delete(user: QuestionDto)

}