package com.sep.byteClub.data.model.weather.airQuality

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AirQualityDto (
    val latitude : Float,
    val longitude : Float,
    val elevation : Float,
    @SerialName("generationtime_ms")
    val generationTimeMs : Float,
    @SerialName("utc_offset_seconds")
    val utcOffsetSeconds : Long,
    val timezone : String,
    @SerialName("timezone_abbreviation")
    val timezoneAbbreviation : String,
    val hourly : HourlyAirQuality
)