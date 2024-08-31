package com.sep.byteClub.domain.repository.weather

import com.sep.byteClub.domain.entiry.weather.AirQualityEntity
import com.sep.byteClub.utils.ResultState

interface AirQualityRepository {

    suspend fun fetchAirQuality(lat: Double, long: Double) : ResultState<AirQualityEntity>

}