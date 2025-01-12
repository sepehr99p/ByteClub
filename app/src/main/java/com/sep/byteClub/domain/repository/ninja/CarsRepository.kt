package com.sep.byteClub.domain.repository.ninja

import com.sep.byteClub.domain.entity.cars.CarEntity
import com.sep.byteClub.utils.ResultState

interface CarsRepository {

    suspend fun fetchCarsList(): ResultState<List<CarEntity>>

    suspend fun findCar(model: String)

}