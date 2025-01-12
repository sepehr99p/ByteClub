package com.sep.byteClub.data.repository.ninja

import com.sep.byteClub.data.remote.ninja.NinjaApiService
import com.sep.byteClub.domain.entity.cars.CarEntity
import com.sep.byteClub.domain.repository.ninja.CarsRepository
import com.sep.byteClub.utils.ResultState
import javax.inject.Inject

class CarsRepositoryImpl @Inject constructor(
    private val ninjaApiService: NinjaApiService
) : CarsRepository {

    override suspend fun fetchCarsList(): ResultState<List<CarEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun findCar(model: String) {
        TODO("Not yet implemented")
    }
}