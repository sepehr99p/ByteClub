package com.sep.byteClub.data.model.response.quiz

import com.sep.byteClub.utils.callAdapter.BaseNetworkResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResetTokenResponse(
    @SerialName("token") val token: String
) : BaseNetworkResponse()