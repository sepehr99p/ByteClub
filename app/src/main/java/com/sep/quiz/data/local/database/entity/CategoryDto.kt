package com.sep.quiz.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sep.quiz.domain.entiry.CategoryEntity


@Entity(tableName = "category")
data class CategoryDto(
    @PrimaryKey val id: Int,
    val name: String
) {
    fun toDomainModel(): CategoryEntity = CategoryEntity(
        id = id.toString(), name = name
    )
}