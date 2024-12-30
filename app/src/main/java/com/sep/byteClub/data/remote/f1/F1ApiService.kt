package com.sep.byteClub.data.remote.f1

import com.sep.byteClub.data.model.response.f1.DriversDto
import com.sep.byteClub.data.model.response.f1.IntervalsDto
import com.sep.byteClub.data.model.response.f1.PositionDto
import com.sep.byteClub.data.model.response.f1.RaceWeatherDto
import retrofit2.Response
import retrofit2.http.GET

interface F1ApiService {

    @GET("v1/drivers")
    suspend fun fetchDrivers() : Response<List<DriversDto>>

    @GET("v1/intervals")
    suspend fun fetchRaceIntervals() : Response<List<IntervalsDto>>

    @GET("v1/position")
    suspend fun fetchPositions() : Response<List<PositionDto>>

    @GET("v1/weather")
    suspend fun fetchRaceWeather() : Response<List<RaceWeatherDto>>

}