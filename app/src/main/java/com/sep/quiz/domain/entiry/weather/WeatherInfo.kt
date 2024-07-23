package com.sep.quiz.domain.entiry.weather

import com.sep.quiz.domain.entiry.weather.WeatherData

data class WeatherInfo(
    val weatherDataPerDay: Map<Int, List<WeatherData>>,
    val currentWeatherData: WeatherData?
)