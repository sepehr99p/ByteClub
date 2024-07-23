package com.sep.quiz.domain.usecase.weather

import com.sep.quiz.domain.repository.weather.WeatherRepository

class ForecastWeatherUseCase constructor(
    private val weatherRepository: WeatherRepository
) {

    suspend operator fun invoke(lat: Double, long: Double) =
        weatherRepository.getForecast(lat, long)
}