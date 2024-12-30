package com.sep.byteClub.domain.entity.crypto

data class Ticker24hEntity(
    val changeRate : String,
    val changePrice : String,
    val high : String,
    val low : String,
    val vol : String,
    val last : String,
    val averagePrice : String
)
