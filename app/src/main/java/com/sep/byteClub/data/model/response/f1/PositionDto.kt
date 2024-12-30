package com.sep.byteClub.data.model.response.f1

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PositionDto(
    @SerialName("date") val date : String, //2023-08-26T09:30:47.199000+00:00
    @SerialName("driver_number") val driverNumber : Long,
    @SerialName("meeting_key") val meetingKey : Long,
    @SerialName("position") val position : Long,
    @SerialName("session_key") val sessionKey : Long,
)
