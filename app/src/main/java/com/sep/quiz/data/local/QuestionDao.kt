package com.sep.quiz.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.sep.quiz.data.local.entity.QuestionDto

@Dao
interface QuestionDao {

    @Query("SELECT * FROM questions")
    fun fetchAll() : List<QuestionDto>

    @Query("SELECT * FROM questions WHERE category IS (:cat)")
    fun loadAllByIds(cat: String): List<QuestionDto>

    @Insert
    fun insertAll(vararg questions: QuestionDto)

    @Delete
    fun delete(user: QuestionDto)

}