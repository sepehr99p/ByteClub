package com.sep.byteClub.data.repository.f1

import com.sep.byteClub.data.remote.f1.F1ApiService
import com.sep.byteClub.domain.entity.f1.DriverEntity
import com.sep.byteClub.domain.entity.f1.IntervalEntity
import com.sep.byteClub.domain.entity.f1.RaceMeetingEntity
import com.sep.byteClub.domain.entity.f1.RacePositionEntity
import com.sep.byteClub.domain.entity.f1.RaceWeatherEntity
import com.sep.byteClub.domain.repository.f1.F1Repository
import com.sep.byteClub.utils.ResultState
import com.sep.byteClub.utils.toResultState
import jakarta.inject.Inject

class F1RepositoryImpl @Inject constructor(
    private val f1ApiService: F1ApiService
) : F1Repository {


    override suspend fun fetchDrivers(): ResultState<List<DriverEntity>> {
        return f1ApiService.fetchDrivers().toResultState(
            onSuccess = {
                ResultState.Success(it.map { it.toDomainModel() })
            }
        )
    }

    override suspend fun fetchIntervals(): ResultState<List<IntervalEntity>> {
        return f1ApiService.fetchRaceIntervals().toResultState(
            onSuccess = {
                ResultState.Success(it.map { it.toDomainModel() })
            }
        )
    }

    override suspend fun fetchPositions(): ResultState<List<RacePositionEntity>> {
        return f1ApiService.fetchPositions().toResultState(
            onSuccess = {
                ResultState.Success(it.map { it.toDomainModel() })
            }
        )
    }

    override suspend fun fetchRaceWeather(): ResultState<List<RaceWeatherEntity>> {
        return f1ApiService.fetchRaceWeather().toResultState(
            onSuccess = {
                ResultState.Success(it.map { it.toDomainModel() })
            }
        )
    }

    override suspend fun fetchRaceMeetings(): ResultState<List<RaceMeetingEntity>> {
        return f1ApiService.fetchMeetings().toResultState(
            onSuccess = {
                ResultState.Success(it.map { it.toDomainModel() })
            }
        )
    }
}