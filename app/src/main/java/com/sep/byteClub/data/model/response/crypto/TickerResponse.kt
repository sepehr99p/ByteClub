package com.sep.byteClub.data.model.response.crypto

import com.sep.byteClub.domain.entiry.crypto.TickerEntity
import kotlinx.serialization.Serializable

@Serializable
data class TickerResponse(
    val symbol: String? = null,
    val symbolName: String? = null,
    val buy: String? = null,
    val sell: String? = null,
    val bestBidSize: String? = null,
    val bestAskSize: String? = null,
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
    fun toDomainModel(): TickerEntity = TickerEntity(
        symbol = symbol ?: "",
        symbolName = symbolName ?: "",
        buy = buy ?: "",
        sell = sell ?: "",
        bestBidSize = bestBidSize ?: "",
        bestAskSize = bestAskSize ?: "",
        changeRate = changeRate ?: "",
        changePrice = changePrice ?: "",
        high = high ?: "",
        low = low ?: "",
        vol = vol ?: "",
        volValue = volValue ?: "",
        last = last ?: "",
        averagePrice = averagePrice ?: "null :/",
        takerFeeRate = takerFeeRate ?: "",
        makerFeeRate = makerFeeRate ?: "",
        takerCoefficient = takerCoefficient ?: "",
        makerCoefficient = makerCoefficient ?: ""
    )
}

