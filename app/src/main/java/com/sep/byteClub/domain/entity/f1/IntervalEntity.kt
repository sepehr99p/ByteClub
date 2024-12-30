package com.sep.byteClub.domain.entity.f1

data class IntervalEntity(
    val date : String,
    val driverNumber : Long,
    val gapToLeader : Double,
    val interval : Double,
)
