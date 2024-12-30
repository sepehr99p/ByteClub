package com.sep.byteClub.data.model.response.quiz

import com.sep.byteClub.data.local.database.entity.CategoryDto
import com.sep.byteClub.domain.entity.CategoryEntity
import com.sep.byteClub.utils.callAdapter.BaseNetworkResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryResponse(
    @SerialName("trivia_categories") val categories: List<CategoryResponseItem>
) : BaseNetworkResponse()


@Serializable
data class CategoryResponseItem(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String
) {
    fun toDomainModel(): CategoryEntity = CategoryEntity(
        id = this.id.toString(),
        name = this.name
    )

    fun toDatabaseDto(): CategoryDto = CategoryDto(
        id = id,
        name = name
    )
}
