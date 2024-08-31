package com.sep.byteClub.domain.usecase.weather

import com.sep.byteClub.domain.repository.weather.AirQualityRepository

class AirQualityUseCase(
    private val airQualityRepository: AirQualityRepository
) {
    suspend operator fun invoke(lat: Double, long: Double) =
        airQualityRepository.fetchAirQuality(lat, long)
}