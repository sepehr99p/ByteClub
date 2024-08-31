package com.sep.byteClub.data.model.response.crypto

import com.sep.byteClub.domain.entiry.crypto.Ticker24hEntity
import kotlinx.serialization.Serializable

@Serializable
data class Ticker24hResponse(
    val time: Long? = null,
    val symbol: String? = null,
    val buy: String? = null,
    val sell: String? = null,
    val changeRate: String? = null,
    val changePrice: String? = null,
    val high: String? = null,
    val low: String? = null,
    val vol: String? = null,
    val volValue: String? = null,
    val last: String? = null,
    val averagePrice: String? = null,
    val takerFeeRate: String? = null,
    val makerFeeRate: String? = null,
    val takerCoefficient: String? = null,
    val makerCoefficient: String? = null
) {
    fun toDomainModel(): Ticker24hEntity = Ticker24hEntity(
        changeRate = changeRate ?: "0",
        changePrice = changePrice ?: "0",
        high = high ?: "0",
        low = low ?: "0",
        last = last ?: "0",
        vol = vol ?: "0",
        averagePrice = averagePrice ?: "0"
    )
}

