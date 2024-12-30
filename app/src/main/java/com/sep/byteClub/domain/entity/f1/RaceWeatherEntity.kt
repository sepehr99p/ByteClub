package com.sep.byteClub.domain.entity.f1

data class RaceWeatherEntity(
    val airTempC : Double,
    val trackTempC : Double,
    val windSpeed : Double,
    val date : String,
    val humidity : Long,
    val meetingKey : Long,
    val rainfall : Long,
    val sessionKey : Long,
)
