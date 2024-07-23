package com.sep.quiz.domain.repository.weather

import com.sep.quiz.domain.entiry.weather.AirQualityEntity
import com.sep.quiz.utils.ResultState

interface AirQualityRepository {

    suspend fun fetchAirQuality(lat: Double, long: Double) : ResultState<AirQualityEntity>

}