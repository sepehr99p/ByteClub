package com.sep.byteClub.domain.entity.crypto

data class CandleEntity(
    val time : String,
    val opening : String,
    val closing : String,
    val highest : String,
    val lowest : String,
    val volume : String,
    val amount : String
)
