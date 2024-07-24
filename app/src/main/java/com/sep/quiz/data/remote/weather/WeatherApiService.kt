package com.sep.quiz.data.remote.weather

import com.sep.quiz.data.model.weather.ForecastDto
import com.sep.quiz.data.model.weather.WeatherDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("v1/forecast?hourly=temperature_2m,weathercode,relativehumidity_2m,windspeed_10m,pressure_msl")
    suspend fun getWeatherData(
        @Query("latitude") lat: Double,
        @Query("longitude") long: Double
    ): Response<WeatherDto>

    @GET("v1/forecast?daily=weathercode,temperature_2m_max,temperature_2m_min,rain_sum,snowfall_sum")
    suspend fun getDailyForecast(
        @Query("latitude") lat: Double,
        @Query("longitude") long: Double
    ) : Response<ForecastDto>




}