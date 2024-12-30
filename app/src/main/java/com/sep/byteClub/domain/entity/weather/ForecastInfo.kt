package com.sep.byteClub.domain.entity.weather

data class ForecastInfo(
    val time: List<String>,
    val maxTemperatures: List<Double>,
    val minTemperatures: List<Double>,
    val weatherCodes: List<Int>,
    val rainSum: List<Double>,
    val snowfallSum: List<Double>

)