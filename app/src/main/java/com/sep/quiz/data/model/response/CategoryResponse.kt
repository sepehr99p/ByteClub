package com.sep.quiz.data.model.response

import com.sep.quiz.data.utils.callAdapter.BaseNetworkResponse
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
)
