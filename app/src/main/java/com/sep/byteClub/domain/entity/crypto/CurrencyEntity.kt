package com.sep.byteClub.domain.entity.crypto

data class CurrencyEntity(
    val currency : String,
    val name : String,
    val fullName : String,
    val precision : Int
)
