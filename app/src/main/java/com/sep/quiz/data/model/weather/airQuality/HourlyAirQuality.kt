package com.sep.quiz.data.model.weather.airQuality

import kotlinx.serialization.Serializable

@Serializable
data class HourlyAirQuality(
    val time : List<String>,
    val pm10 : List<Float>
)
