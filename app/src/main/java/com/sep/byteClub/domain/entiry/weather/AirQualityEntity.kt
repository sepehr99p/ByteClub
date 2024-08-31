package com.sep.byteClub.domain.entiry.weather

data class AirQualityEntity(
    val elevation : Float,
    val timezoneAbbreviation : String,
    val hourly : HourlyAirQualityEntity,
    val daily : DailyAirQualityEntity
)
