package com.sep.byteClub.domain.repository.f1

import com.sep.byteClub.domain.entity.f1.DriverEntity
import com.sep.byteClub.domain.entity.f1.IntervalEntity
import com.sep.byteClub.domain.entity.f1.RaceMeetingEntity
import com.sep.byteClub.domain.entity.f1.RacePositionEntity
import com.sep.byteClub.domain.entity.f1.RaceWeatherEntity
import com.sep.byteClub.utils.ResultState

interface F1Repository {

    suspend fun fetchDrivers() : ResultState<List<DriverEntity>>

    suspend fun fetchIntervals() : ResultState<List<IntervalEntity>>

    suspend fun fetchPositions() : ResultState<List<RacePositionEntity>>

    suspend fun fetchRaceWeather() : ResultState<List<RaceWeatherEntity>>

    suspend fun fetchRaceMeetings() : ResultState<List<RaceMeetingEntity>>

}