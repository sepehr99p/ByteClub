package com.sep.quiz.data.model.response

import com.sep.quiz.data.utils.callAdapter.BaseNetworkResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RetrieveTokenResponse(
    @SerialName("token") val token : String
) : BaseNetworkResponse()
