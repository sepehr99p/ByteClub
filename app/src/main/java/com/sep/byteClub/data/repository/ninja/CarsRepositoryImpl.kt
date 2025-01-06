package com.sep.byteClub.data.repository.ninja

import com.sep.byteClub.data.remote.ninja.NinjaApiService
import com.sep.byteClub.domain.repository.ninja.CarsRepository
import javax.inject.Inject

class CarsRepositoryImpl @Inject constructor(
    private val ninjaApiService: NinjaApiService
) : CarsRepository {

    override suspend fun fetchCarsList() {
        TODO("Not yet implemented")
    }

    override suspend fun findCar(model: String) {
        TODO("Not yet implemented")
    }
}