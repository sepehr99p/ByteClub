package com.sep.byteClub.data.model.response.imageToText

import com.sep.byteClub.utils.callAdapter.BaseNetworkResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ImageToTextDto(
    @SerialName("text") val text : String
)
