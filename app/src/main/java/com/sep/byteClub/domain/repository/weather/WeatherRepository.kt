package com.sep.byteClub.domain.repository.weather

import com.sep.byteClub.domain.entiry.weather.ForecastInfo
import com.sep.byteClub.domain.entiry.weather.WeatherInfo
import com.sep.byteClub.utils.ResultState

interface WeatherRepository {

    suspend fun getWeatherData(lat: Double, long: Double): ResultState<WeatherInfo>
    suspend fun getForecast(lat: Double, long: Double): ResultState<ForecastInfo>

}