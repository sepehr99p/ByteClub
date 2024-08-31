package com.sep.byteClub.data.repository.weather

import com.sep.byteClub.data.model.weather.mapper.toDomainModel
import com.sep.byteClub.data.remote.weather.WeatherApiService
import com.sep.byteClub.domain.entiry.weather.ForecastInfo
import com.sep.byteClub.domain.entiry.weather.WeatherInfo
import com.sep.byteClub.domain.repository.weather.WeatherRepository
import com.sep.byteClub.utils.ResultState
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApiService: WeatherApiService
) : WeatherRepository {

    override suspend fun getWeatherData(lat: Double, long: Double): ResultState<WeatherInfo> {
        val result = weatherApiService.getWeatherData(lat, long)
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
        val result = weatherApiService.getDailyForecast(lat, long)
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