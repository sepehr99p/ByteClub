package com.sep.quiz.domain.repository.weather

import com.sep.quiz.domain.entiry.weather.ForecastInfo
import com.sep.quiz.domain.entiry.weather.WeatherInfo
import com.sep.quiz.utils.ResultState

interface WeatherRepository {

    suspend fun getWeatherData(lat: Double, long: Double): ResultState<WeatherInfo>
    suspend fun getForecast(lat: Double, long: Double): ResultState<ForecastInfo>

}