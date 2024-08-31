package com.sep.byteClub.domain.usecase.weather

import com.sep.byteClub.domain.repository.weather.WeatherRepository

class CurrentWeatherUseCase constructor(
    private val weatherRepository: WeatherRepository
) {

    suspend operator fun invoke(lat: Double, long: Double) =
        weatherRepository.getWeatherData(lat, long)
}