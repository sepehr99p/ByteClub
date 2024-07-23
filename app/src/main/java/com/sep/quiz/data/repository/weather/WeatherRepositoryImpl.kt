package com.sep.quiz.data.repository.weather

import com.sep.quiz.data.model.weather.mapper.toDomainModel
import com.sep.quiz.data.remote.weather.WeatherApi
import com.sep.quiz.domain.entiry.weather.ForecastInfo
import com.sep.quiz.domain.entiry.weather.WeatherInfo
import com.sep.quiz.domain.repository.weather.WeatherRepository
import com.sep.quiz.utils.ResultState
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi
) : WeatherRepository {

    override suspend fun getWeatherData(lat: Double, long: Double): ResultState<WeatherInfo> {
        val result = weatherApi.getWeatherData(lat, long)
        return if (result.isSuccessful) {
            result.body()?.let {
                ResultState.Success(it.toDomainModel())
            } ?: run {
                ResultState.Failure("Empty result")
            }
        } else {
            ResultState.Failure(result.message())
        }
    }

    override suspend fun getForecast(lat: Double, long: Double): ResultState<ForecastInfo> {
        val result = weatherApi.getDailyForecast(lat, long)
        return if (result.isSuccessful) {
            result.body()?.let {
                ResultState.Success(it.toDomainModel())
            } ?: run {
                ResultState.Failure("Empty body")
            }
        } else {
            ResultState.Failure(result.message())
        }
    }


}