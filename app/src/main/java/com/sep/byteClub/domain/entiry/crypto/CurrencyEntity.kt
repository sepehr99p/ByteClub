package com.sep.byteClub.domain.entiry.crypto

data class CurrencyEntity(
    val currency : String,
    val name : String,
    val fullName : String,
    val precision : Int
)
