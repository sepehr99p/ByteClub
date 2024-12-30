package com.sep.byteClub.domain.usecase.f1

import com.sep.byteClub.domain.repository.f1.F1Repository
import javax.inject.Inject

class FetchF1RaceWeatherUseCase @Inject constructor(
    private val f1Repository: F1Repository
) {

    suspend operator fun invoke() = f1Repository.fetchRaceWeather()

}