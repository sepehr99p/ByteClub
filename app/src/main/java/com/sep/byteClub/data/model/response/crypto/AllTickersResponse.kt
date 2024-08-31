package com.sep.byteClub.data.model.response.crypto

import kotlinx.serialization.Serializable

@Serializable
data class AllTickersResponse(
    val time : Long,
    val ticker : List<TickerResponse>
)
