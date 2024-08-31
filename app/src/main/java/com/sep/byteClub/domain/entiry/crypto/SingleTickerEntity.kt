package com.sep.byteClub.domain.entiry.crypto

data class SingleTickerEntity(
    val sequence: String,
    val price: String,
    val size: String,
    val bestAsk: String,
    val bestAskSize: String,
    val bestBid: String,
    val bestBidSize: String,
    val time: Long
)
