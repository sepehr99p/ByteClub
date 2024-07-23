package com.sep.quiz.data.model.weather

import com.sep.quiz.data.model.weather.ForecastDataDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastDto(
    @SerialName("daily")
    val forecastDataDto: ForecastDataDto

)