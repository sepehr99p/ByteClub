package com.sep.byteClub.domain.entity.f1

data class RacePositionEntity(
    val date : String,
    val driverNumber : Long,
    val meetingKey : Long,
    val position : Long,
    val sessionKey : Long,
)
