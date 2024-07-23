package com.sep.quiz.data.remote.weather

import com.sep.quiz.data.model.weather.airQuality.AirQualityDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AirQualityApi {

    @GET("v1/air-quality")
    suspend fun fetchAirQuality(
        @Query("latitude") lat: Double,
        @Query("longitude") long: Double,
        @Query("hourly") hourly : String
    ) : Response<AirQualityDto>

}