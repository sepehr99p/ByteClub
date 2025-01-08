package com.sep.byteClub.domain.usecase.cars

import com.sep.byteClub.domain.repository.ninja.CarsRepository
import javax.inject.Inject

class FetchCarsListUseCase @Inject constructor(
    private val carsRepository: CarsRepository
) {

    suspend operator fun invoke() = carsRepository.fetchCarsList()

}