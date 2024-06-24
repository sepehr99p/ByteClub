package com.sep.quiz.data.model.response

import com.sep.quiz.data.utils.callAdapter.BaseNetworkResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuestionCountResponse(
    @SerialName("category_id") val id : String,
    @SerialName("category_question_count") val categoryCountInfo : CategoryCountInfo
) : BaseNetworkResponse()

@Serializable
data class CategoryCountInfo(
    @SerialName("total_question_count") val total : Int,
    @SerialName("total_easy_question_count") val easyCount : Int,
    @SerialName("total_medium_question_count") val mediumCount : Int,
    @SerialName("total_hard_question_count") val hardCount : Int
)
