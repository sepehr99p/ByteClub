package com.sep.quiz.data.model.response

import com.sep.quiz.domain.entiry.CategoryEntity
import com.sep.quiz.utils.callAdapter.BaseNetworkResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryResponse(
    @SerialName("trivia_categories") val categories: List<CategoryResponseItem>
) : BaseNetworkResponse()


@Serializable
data class CategoryResponseItem(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String
) {
    fun toDomainModel() : CategoryEntity = CategoryEntity(
        id = this.id,
        name = this.name
    )
}
