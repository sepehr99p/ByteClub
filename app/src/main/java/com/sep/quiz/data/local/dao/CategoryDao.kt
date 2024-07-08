package com.sep.quiz.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sep.quiz.data.local.entity.CategoryDto

@Dao
interface CategoryDao {

    @Query("SELECT * FROM category")
    fun fetchAll() : List<CategoryDto>

    @Query("SELECT * FROM category WHERE id IS (:id)")
    fun loadById(id: Int): CategoryDto

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg categories: CategoryDto)

    @Delete
    fun delete(user: CategoryDto)

}