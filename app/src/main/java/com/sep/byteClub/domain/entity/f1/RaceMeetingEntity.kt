package com.sep.byteClub.domain.entity.f1

data class RaceMeetingEntity(
    val circuitKey: Long,
    val meetingKey: Long,
    val shortName: String,
    val name: String,
    val dateStart: String,
    val gmtOffset: String,
)
