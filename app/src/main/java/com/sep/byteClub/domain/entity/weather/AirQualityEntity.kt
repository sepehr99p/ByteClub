package com.sep.byteClub.domain.entity.weather

data class AirQualityEntity(
    val elevation : Float,
    val timezoneAbbreviation : String,
    val hourly : HourlyAirQualityEntity,
    val daily : DailyAirQualityEntity
)
