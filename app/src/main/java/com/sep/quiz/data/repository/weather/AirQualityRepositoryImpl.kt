package com.sep.quiz.data.repository.weather

import com.sep.quiz.data.model.weather.mapper.toDomainModel
import com.sep.quiz.data.remote.weather.AirQualityApi
import com.sep.quiz.domain.DEFAULT_ERROR
import com.sep.quiz.domain.entiry.weather.AirQualityEntity
import com.sep.quiz.domain.repository.weather.AirQualityRepository
import com.sep.quiz.utils.ResultState
import javax.inject.Inject

class AirQualityRepositoryImpl @Inject constructor(
    private val airQualityApi: AirQualityApi
) : AirQualityRepository {

    override suspend fun fetchAirQuality(lat: Double, long: Double): ResultState<AirQualityEntity> {
        val result = airQualityApi.fetchAirQuality(lat, long, "pm10")
        return if (result.isSuccessful) {
            result.body()?.let {
                ResultState.Success(it.toDomainModel())
            } ?: run {
                ResultState.Failure("Empty response")
            }
        } else {
            ResultState.Failure(DEFAULT_ERROR)
        }
    }

}