package com.sep.byteClub.domain.entity.f1

data class DriverEntity(
    val broadcastName : String,
    val number : Long,
    val fullName : String,
    val acronym : String,
    val facePictureUrl : String,
    val teamColor : String,
    val teamName : String,
)
