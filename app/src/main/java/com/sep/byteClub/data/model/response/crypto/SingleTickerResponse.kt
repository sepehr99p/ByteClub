package com.sep.byteClub.data.model.response.crypto

import com.sep.byteClub.domain.entity.crypto.SingleTickerEntity
import kotlinx.serialization.Serializable

@Serializable
data class SingleTickerResponse(
    val sequence: String,
    val price: String,
    val size: String,
    val bestAsk: String,
    val bestAskSize: String,
    val bestBid: String,
    val bestBidSize: String,
    val time: Long
) {
    fun toDomainModel(): SingleTickerEntity = SingleTickerEntity(
        sequence = sequence,
        price = price,
        size = size,
        bestAsk = bestAsk,
        bestAskSize = bestAskSize,
        bestBid = bestBid,
        bestBidSize = bestBidSize,
        time = time
    )
}

