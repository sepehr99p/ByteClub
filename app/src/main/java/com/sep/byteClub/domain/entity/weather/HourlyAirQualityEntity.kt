package com.sep.byteClub.domain.entity.weather

data class HourlyAirQualityEntity(
    val time : List<String>,
    val pm10 : List<Float>
)
