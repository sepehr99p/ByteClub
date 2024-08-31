package com.sep.byteClub.domain.usecase.weather

import com.sep.byteClub.domain.repository.weather.WeatherRepository

class ForecastWeatherUseCase constructor(
    private val weatherRepository: WeatherRepository
) {

    suspend operator fun invoke(lat: Double, long: Double) =
        weatherRepository.getForecast(lat, long)
}