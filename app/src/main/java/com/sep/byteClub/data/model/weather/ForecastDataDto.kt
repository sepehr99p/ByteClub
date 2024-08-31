package com.sep.byteClub.data.model.weather

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastDataDto(
    val time: List<String>,

    @SerialName("temperature_2m_max")
    val maxTemperatures: List<Double>,
    @SerialName("temperature_2m_min")
    val minTemperatures: List<Double>,
    @SerialName("weathercode")
    val weatherCodes: List<Int>,
    @SerialName("rain_sum")
    val rainSum: List<Double>,
    @SerialName("snowfall_sum")
    val snowfallSum: List<Double>

)