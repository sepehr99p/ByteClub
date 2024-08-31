package com.sep.byteClub.data.model.weather

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastDto(
    @SerialName("daily")
    val forecastDataDto: ForecastDataDto

)