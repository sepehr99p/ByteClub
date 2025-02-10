package com.sep.byteClub.domain.entity.cars

data class CarEntity(
    val city : Int,
    val carClass : String,
    val combination : Int,
    val cylinders : Int,
    val displacement : Double,
    val highway : Int,
    val year : Int,
    val drive : String,
    val fuelType : String,
    val make : String,
    val model : String,
    val transmission : String,
)