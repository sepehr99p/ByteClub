package com.sep.quiz.data.model.response.imageToText

import com.sep.quiz.utils.callAdapter.BaseNetworkResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageToTextResponse(
    val list: List<ImageToTextDto>
): BaseNetworkResponse()

@Serializable
data class ImageToTextDto(
    @SerialName("text") val text : String
)
