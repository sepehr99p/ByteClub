package com.sep.byteClub.data.model.response.f1

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RaceWeatherDto(
    @SerialName("air_temperature") val airTempC : Double,
    @SerialName("track_temperature") val trackTempC : Double,
    @SerialName("wind_speed") val windSpeed : Double,
    @SerialName("date") val date : String,
    @SerialName("humidity") val humidity : Long,
    @SerialName("meeting_key") val meetingKey : Long,
    @SerialName("rainfall") val rainfall : Long,
    @SerialName("session_key") val sessionKey : Long,
)
