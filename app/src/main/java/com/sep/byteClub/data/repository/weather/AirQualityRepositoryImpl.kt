package com.sep.byteClub.data.repository.weather

import com.sep.byteClub.data.model.weather.mapper.toDomainModel
import com.sep.byteClub.data.remote.weather.AirQualityApi
import com.sep.byteClub.domain.DEFAULT_ERROR
import com.sep.byteClub.domain.entity.weather.AirQualityEntity
import com.sep.byteClub.domain.repository.weather.AirQualityRepository
import com.sep.byteClub.utils.ResultState
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