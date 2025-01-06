package com.sep.byteClub.domain.repository.ninja

interface CarsRepository {

    suspend fun fetchCarsList()

    suspend fun findCar(model : String)

}