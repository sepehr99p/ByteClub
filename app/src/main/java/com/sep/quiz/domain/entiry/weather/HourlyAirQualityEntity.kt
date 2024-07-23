package com.sep.quiz.domain.entiry.weather

data class HourlyAirQualityEntity(
    val time : List<String>,
    val pm10 : List<Float>
)
